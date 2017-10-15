package com.parsonswang.zxfootball;

import android.app.Application;

import com.parsonswang.zxfootball.core.LogInit;


/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LogInit.initLog();
    }
}
