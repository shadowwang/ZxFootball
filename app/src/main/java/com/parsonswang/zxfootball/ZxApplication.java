package com.parsonswang.zxfootball;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.glidebitmappool.GlideBitmapPool;
import com.parsonswang.zxfootball.core.ApplicationContextHolder;
import com.parsonswang.zxfootball.core.CrashReportInit;
import com.parsonswang.zxfootball.core.LogInit;
import com.parsonswang.zxfootball.core.MtaInit;
import com.tencent.bugly.crashreport.CrashReport;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import timber.log.Timber;


/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {

    public static Application appInstance;

    public FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        LogInit.initLog();

        MtaInit.initMta(appInstance);

        CrashReportInit.initCrashReport();

        Stetho.initializeWithDefaults(this);

        CrashReport.initCrashReport(getApplicationContext(), "ce5d70e35c", false);

        GlideBitmapPool.initialize(10 * 1024 * 1024) ;

        initFlutterEngine();
        Timber.i("---onCreate---");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Timber.i("---attachBaseContext---");
        ApplicationContextHolder.setContext(base);
    }

    private void initFlutterEngine() {
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
    }

}
