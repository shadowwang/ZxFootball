package com.parsonswang.common.monitor;

/**
 * Created by parsonswang on 2017/10/17.
 */

public enum MonitorFactory {
    INSTANCE;

    public IMonitor getMonitorFactory() {
        return new MtaMonitorImpl();
    }
}
