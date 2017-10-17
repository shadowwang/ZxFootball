package com.parsonswang.common.monitor;

import android.content.Context;

import java.util.Map;

/**
 * Created by parsonswang on 2017/10/17.
 */

public interface IMonitor {

    //异常监控
    void crashMonitor(Context context);

    //网速监控
    void netSpeedMonitor(Map<String, Integer> map);
}
