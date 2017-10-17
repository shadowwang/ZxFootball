package com.parsonswang.zxfootball.core;

import android.content.Context;

/**
 * Created by parsonswang on 2017/10/17.
 */

public class ApplicationContextHolder {

    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
