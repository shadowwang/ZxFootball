package com.parsonswang.zxfootball.common.utils;

import com.tencent.stat.StatConfig;

/**
 * Created by wangchun on 2017/10/22.
 */

public class ConfigUtil {

    public static String getOnlineConfig(String key, String defaultVal) {
        return StatConfig.getCustomProperty(key, defaultVal);
    }

    public static String getOnlineConfig(String key) {
        return getOnlineConfig(key, "");
    }
}
