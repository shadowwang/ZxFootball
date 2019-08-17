package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsonswang.zxfootball.R;

/**
 * Created by parsonswang on 2017/10/18.
 */

public class CommonActionBar extends FrameLayout {

    private TextView mTitleView;
    private ImageView mLeftView, mRightView;

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
        mTitleView = view.findViewById(R.id.tv_title);
        mLeftView = view.findViewById(R.id.mIvLeft);
        mRightView = view.findViewById(R.id.mRightView);
        addView(view);
    }

    public void setTitle(String text) {
        if (mTitleView == null) {
            return;
        }

        mTitleView.setText(text);
    }

    public void showLeftArrow() {
        if (mLeftView == null) {
            return;
        }

        mLeftView.setVisibility(VISIBLE);
    }

    public void setLeftArrowClickListener(OnClickListener onClickListener) {
        if (mLeftView == null || onClickListener == null) {
            return;
        }

        mLeftView.setOnClickListener(onClickListener);
    }

    public void hideLeftArrow() {
        if (mLeftView == null) {
            return;
        }

        mLeftView.setVisibility(GONE);
    }

    public void hideRightArrow() {
        if (mRightView == null) {
            return;
        }

        mRightView.setVisibility(GONE);
    }

}
