package com.parsonswang.common.utils;

/**
 * Created by parsonswang on 2017/10/16.
 */

public class StringUtils {

    public static boolean isEmptyString(String s) {
        return (s == null || s.trim().length() == 0);
    }
}
