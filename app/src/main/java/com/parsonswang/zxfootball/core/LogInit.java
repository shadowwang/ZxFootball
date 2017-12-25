package com.parsonswang.zxfootball.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.zxfootball.BuildConfig;
import com.parsonswang.zxfootball.ZxApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/15.
 */

public class LogInit {

    private static ExecutorService sExecutor;

    private static String sDefaultDir;

    static {
        if (StringUtils.isEmptyString(sDefaultDir)) {
            if (Environment.MEDIA_MOUNTED.equals(ZxApplication.appInstance.getExternalCacheDir())
                    && (ZxApplication.appInstance != null && ZxApplication.appInstance.getExternalCacheDir() != null)) {
                sDefaultDir = ZxApplication.appInstance.getExternalCacheDir() + File.separator + "log" + File.separator;
            } else {
                sDefaultDir = ZxApplication.appInstance.getCacheDir() + File.separator + "log" + File.separator;
            }
        }

    }

    private static class ZxLogReleaseTree extends Timber.Tree {

        @Override
        protected boolean isLoggable(String tag, int priority) {
            //只有debug级别的才能打印日志
            if (priority == Log.DEBUG) {
                return super.isLoggable(tag, priority);
            }
            return false;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (!isLoggable(tag, priority)) {
                return;
            }
            logByPrintFile();
        }
    }

    private static void logByPrintFile() {
        String appEnvironment = "MODEL:" + Build.MODEL + '\n'
                + "BOARD:" + Build.BOARD + '\n'
                + "BRAND:" + Build.BRAND + '\n'
                + "DEVICE:" + Build.DEVICE + '\n'
                + "PRODUCT:" + Build.PRODUCT + '\n'
                + "DISPLAY:" + Build.DISPLAY + '\n'
                + "HOST:" + Build.HOST + '\n'
                + "ID:" + Build.ID + '\n'
                + "USER:" + Build.USER + '\n'
                + "CPU_ABI:" + Build.CPU_ABI + '\n';

        try {
            final Context context = ApplicationContextHolder.getContext();
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            int versionCode = info.versionCode;
            appEnvironment += String.format("AppVersion:(%s)(%04d)", versionName, versionCode);

            final String content = appEnvironment;
            String fileNameTimeStamp = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(new Date());
            String fileName = "ZxFootballLogs_" + fileNameTimeStamp + ".txt";
            final File file = new File(sDefaultDir + fileName);

            if (!createOrExistsFile(file)) {
                return;
            }

            if (sExecutor == null) {
                sExecutor = Executors.newSingleThreadExecutor();
            }

            sExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    BufferedWriter bw = null;
                    try {
                        bw = new BufferedWriter(new FileWriter(file, true));
                        bw.write(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                bw = null;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (PackageManager.NameNotFoundException e) {}
    }

    private static boolean createOrExistsFile(final File file) {
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    public static void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ZxLogReleaseTree());
        }
    }
}
