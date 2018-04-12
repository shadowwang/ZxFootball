package com.parsonswang.common.swipeback;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.lang.ref.WeakReference;

public class SwipeLayoutHelper {

    private WeakReference<Activity> mWeakActivity;
    private SwipeBackLayout mSwipeBackLayout;
    private Activity mActivity;

    public SwipeLayoutHelper(WeakReference<Activity> weakActivity) {
        this.mWeakActivity = weakActivity;
    }

    public void onActivityCreated() {
        mActivity = mWeakActivity == null ? null : mWeakActivity.get();
        if (mActivity == null) {
            return;
        }

        mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mActivity.getWindow().getDecorView().setBackgroundDrawable(null);

        mSwipeBackLayout = new SwipeBackLayout(mActivity);
        mSwipeBackLayout.setCallback(new SwipeBackLayout.Callback() {
            @Override
            public void onSwipeFinish() {
                mActivity.finish();
            }
        });
    }

    public void onPoseCreated() {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.attachActivity(mActivity);
        }
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout;
    }
}
