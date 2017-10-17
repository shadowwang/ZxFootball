package com.parsonswang.zxfootball.core;

import com.parsonswang.common.monitor.MonitorFactory;

/**
 * Created by parsonswang on 2017/10/17.
 */

public class CrashReportInit {

    public static void initCrashReport() {
        MonitorFactory.INSTANCE.getMonitorFactory().crashMonitor(ApplicationContextHolder.getContext());
    }
}
