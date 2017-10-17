package com.parsonswang.zxfootball;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.parsonswang.zxfootball.core.ApplicationContextHolder;
import com.parsonswang.zxfootball.core.CrashReportInit;
import com.parsonswang.zxfootball.core.LogInit;
import com.parsonswang.zxfootball.core.MtaInit;

import timber.log.Timber;


/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {

    public static Application appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("---onCreate---");
        appInstance = this;

        LogInit.initLog();

        MtaInit.initMta(this);

        CrashReportInit.initCrashReport();

        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Timber.i("---attachBaseContext---");
        ApplicationContextHolder.setContext(base);
    }
}
