package com.parsonswang.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by parsonswang on 2017/10/23.
 */

public class DateUtils {

    private static final DateFormat DEFAULT_MATCH_TIME_FORMAT = new SimpleDateFormat("yyyy.mm.dd", Locale.getDefault());

    public static String date2String(Date date, DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static String date2String(Date date) {
        return date2String(date, DEFAULT_MATCH_TIME_FORMAT);
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
}
