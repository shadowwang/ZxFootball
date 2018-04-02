package com.parsonswang.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parsonswang on 2017/10/16.
 */

public class StringUtils {

    public static boolean isEmptyString(String s) {
        return (s == null || s.trim().length() == 0);
    }

    public static Map<String, String> kvToKeyString(String[] kvStr, String splitStr) {
        Map<String, String> kvMap = new HashMap<>();

        for (String kv : kvStr) {
            if (kv.contains(splitStr)) {
                final String[] kvArr = kv.split(splitStr);
                kvMap.put(kvArr[0].trim(), kvArr[1].trim());
            }
        }

        return kvMap;
    }
}
