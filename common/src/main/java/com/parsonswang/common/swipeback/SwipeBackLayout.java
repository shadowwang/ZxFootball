package com.parsonswang.common.swipeback;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SwipeBackLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mContentView;
    private int mLeftMoveDistance;
    private boolean mIsClose;

    public SwipeBackLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mContentView;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                SwipeBackLayout.this.mLeftMoveDistance = left;
                if (mIsClose && mLeftMoveDistance == mContentWidth && mCallback != null) {
                    mCallback.onSwipeFinish();
                }
            }

            /**
             * 手指松开时的回调
             * 这里的逻辑是：
             * 当滑动距离超过一定数值时，当作关闭处理，否则，reset到原来位置
             * @param releasedChild
             * @param xvel
             * @param yvel
             */
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (SwipeBackLayout.this.mLeftMoveDistance >= mContentWidth / 10) {
                    mIsClose = true;
                    mViewDragHelper.settleCapturedViewAt(mContentWidth, releasedChild.getTop());
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, releasedChild.getTop());
                }
                invalidate();
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left < 0) {
                    return 0;
                }

                return Math.min(left, mContentWidth);
            }

            /**
             * 设置水平拖动距离
             * @param child
             * @return
             */
            @Override
            public int getViewHorizontalDragRange(View child) {
                return mContentWidth;
            }
        });

        //右滑，左边
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    private int mContentWidth;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mContentWidth = mContentView.getMeasuredWidth();
    }

    private boolean mEnable = true;

    public void setEnable(boolean enable) {
        this.mEnable = enable;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mViewDragHelper != null && mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //确保只有一个子view
        if (getChildCount() == 1) {
            mContentView = getChildAt(0);
        } else {
            throw new IllegalStateException("SwipeBackLayout can host only one direct child");
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mEnable) {
            return false;
        }

        if (mViewDragHelper != null) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mViewDragHelper != null) {
            mViewDragHelper.processTouchEvent(event);
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onSwipeFinish();
    }

    public void attachActivity(Activity activity) {
        TypedArray typedArray = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
        int background = typedArray.getResourceId(0, 0);
        typedArray.recycle();

        //1.先将原先Activity的contentView从dectorview中移除
        ViewGroup dectorView = (ViewGroup) activity.getWindow().getDecorView();
        if (mContentView == null) {
            mContentView = dectorView.getChildAt(0);
        }
        mContentView.setBackgroundResource(background);
        dectorView.removeView(mContentView);

        //2.此layout添加子view（即Activity的contentview）
        addView(mContentView);

        //3.最后将此layout添加到dectorview中
        dectorView.addView(this);
    }
}
