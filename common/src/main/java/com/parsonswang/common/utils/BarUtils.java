package com.parsonswang.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by parsonswang on 2017/10/18.
 */

public class BarUtils {

    private static final int DEFAULT_ALPHA = 112;

    private static final String TAG_COLOR = "TAG_COLOR";

    public static void setStatusBarColor(Activity activity, int color) {
        setStatusBarColor(activity, color, DEFAULT_ALPHA, false);
    }

    public static void setStatusBarColor(Activity activity, int color, int alpha) {
        setStatusBarColor(activity, color, alpha, false);
    }

    public static void setStatusBarColor(Activity activity, int color,
                                         int alpha, boolean isDector) {
        //4.4以上版本忽略
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        //先将状态栏变透明
        makeStatusbarTransparent(activity);

        //添加状态栏颜色
        addStausBarColor(activity, color, alpha, isDector);
    }

    private static void makeStatusbarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        final Window window = activity.getWindow();
        //5.0以上
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void addStausBarColor(Activity activity, int color,
                                         int alpha, boolean isDecor) {
        ViewGroup parent = isDecor ?
                (ViewGroup) activity.getWindow().getDecorView() :
                (ViewGroup) activity.findViewById(android.R.id.content);
        View fakeStatusBarView = parent.findViewWithTag(TAG_COLOR);
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.getVisibility() == View.GONE) {
                fakeStatusBarView.setVisibility(View.VISIBLE);
            }
            fakeStatusBarView.setBackgroundColor(getStatusBarColor(color, alpha));
        } else {
            parent.addView(createColorStatusBarView(parent.getContext(), color, alpha));
        }
    }

    private static int getStatusBarColor(final int color, final int alpha) {
        if (alpha == 0) return color;
        float a = 1 - alpha / 255f;
        int red = (color >> 16) & 0xff;
        int green = (color >> 8) & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return Color.argb(255, red, green, blue);
    }

    /**
     * 绘制一个和状态栏一样高的颜色矩形
     */
    private static View createColorStatusBarView(final Context context, final int color, final int alpha) {
        View statusBarView = new View(context);
        statusBarView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(context)));
        statusBarView.setBackgroundColor(getStatusBarColor(color, alpha));
        statusBarView.setTag(TAG_COLOR);
        return statusBarView;
    }

    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
