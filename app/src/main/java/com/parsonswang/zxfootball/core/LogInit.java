package com.parsonswang.zxfootball.core;

import android.util.Log;

import com.parsonswang.zxfootball.BuildConfig;

import java.util.logging.Logger;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/15.
 */

public class LogInit {

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
            //换成文件
            Log.d(tag, message);
        }
    }

    public static void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ZxLogReleaseTree());
        }
    }
}
