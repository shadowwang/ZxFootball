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

    private static final DateFormat DEFAULT_MATCH_TIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

    private static final DateFormat MATCH_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static String date2String(Date date, DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static String date2String(Date date) {
        return date2String(date, DEFAULT_MATCH_TIME_FORMAT);
    }

    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = MATCH_TIME_FORMAT;
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到距离当前n月之前的第一天
     * @param date
     * @param rollBackMonth
     * @return
     */
    public static Date getSomeMonthOfFirstDay(Date date, int rollBackMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -rollBackMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 得到距离当前n月之前的最后一天
     * @param date
     * @param rollBackMonth
     * @return
     */
    public static Date getSomeMonthOfLastDay(Date date, int rollBackMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -rollBackMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 得到本月的第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        return firstDayOfMonth;
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设定当前时间为每月一号
        // 当前日历的天数上-1变成最大值 , 此方法不会改变指定字段之外的字段
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

}
