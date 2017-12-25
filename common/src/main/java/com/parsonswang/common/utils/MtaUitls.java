package com.parsonswang.common.utils;

import android.content.Context;

import com.tencent.stat.StatCrashReporter;

/**
 * Created by parsonswang on 2017/10/17.
 */

public class MtaUitls {

    public void reportCrash(Context context) {
        StatCrashReporter crashReporter = StatCrashReporter.getStatCrashReporter(context);
        // 开启异常时的实时上报
        crashReporter.setEnableInstantReporting(true);
        // 开启java异常捕获
        crashReporter.setJavaCrashHandlerStatus(true);
    }
}
