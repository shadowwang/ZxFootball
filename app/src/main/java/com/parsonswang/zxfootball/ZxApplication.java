package com.parsonswang.zxfootball;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {


    private static class CrashReportingTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

        }
    }

}
