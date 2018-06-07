package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import timber.log.Timber;

/**
 * 比赛球员信息container
 */
public class MatchContainerLayout extends FrameLayout {

    public MatchContainerLayout(@NonNull Context context) {
        super(context);
    }

    public MatchContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Timber.i("left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }
}
