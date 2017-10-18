package com.parsonswang.zxfootball.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.parsonswang.zxfootball.R;

/**
 * Created by parsonswang on 2017/10/18.
 */

public class CommonActionBar extends FrameLayout {

    public CommonActionBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CommonActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_actionbar, null, false);
        addView(view);
    }
}
