package com.parsonswang.common.utils;

import android.content.Context;

/**
 * Created by parsonswang on 2017/11/16.
 */

public class UIUtils {

    /**
     * 将dip转换为px
     * @param c 上下文相关对象
     * @param dip 待转换的数值
     * @return 返回转换的px数值
     */
    public static int dip2px(Context c, float dip) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

}
