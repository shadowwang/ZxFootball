package com.parsonswang.zxfootball.common.utils;

import com.tencent.stat.StatConfig;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class ConfigUtil {

    public static String getOnlineConfig(String key, String defaultVal) {
        String config = StatConfig.getCustomProperty(key, defaultVal);
        Timber.i("config: " + config);
        return config;
    }

    public static String getOnlineConfig(String key) {
        return getOnlineConfig(key, "");
    }
}
