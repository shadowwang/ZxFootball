package com.parsonswang.common.swipeback;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.parsonswang.common.R;

public class SwipeBackLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mContentView;
    private int mLeftMoveDistance;
    private boolean mIsClose;
    private Drawable mShadowDrawable;

    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    private int mScrimColor = DEFAULT_SCRIM_COLOR;

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
                mScrollPercent = Math.abs((float) left
                        / (mContentView.getWidth() + mShadowDrawable.getIntrinsicWidth()));

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

        mShadowDrawable = getResources().getDrawable(R.drawable.shadow_left);
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

    private float mScrimOpacity;
    private float mScrollPercent;

    @Override
    public void computeScroll() {
        super.computeScroll();

        mScrimOpacity = 1 - mScrollPercent;

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

    private int lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mEnable) {
            return false;
        }

        boolean inIntercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (Math.abs(x) < 40) {
                    inIntercepted = true;
                } else {
                    inIntercepted = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    inIntercepted = true;
                } else {
                    inIntercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                inIntercepted = false;
                break;
        }

        lastX = x;
        lastY = y;

        if (!inIntercepted) {
            return false;
        }
        if (mViewDragHelper != null) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mEnable) {
            return false;
        }

        if (mViewDragHelper != null) {
            mViewDragHelper.processTouchEvent(event);
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret = super.drawChild(canvas, child, drawingTime);

        if (child == mContentView && mViewDragHelper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
            drawShadow(canvas, child);
            drawScrim(canvas, child);
        }

        return ret;
    }

    /**
     * 滑动后子view左边的阴影
     * @param canvas
     * @param child
     */
    private void drawShadow(Canvas canvas, View child) {
        final Rect drawRect = new Rect();
        child.getHitRect(drawRect);
        mShadowDrawable.setBounds(drawRect.left - mShadowDrawable.getIntrinsicWidth(),
                drawRect.top, drawRect.left, drawRect.bottom);
        mShadowDrawable.draw(canvas);
    }

    /**
     * 绘制剩余的透明度
     * @param canvas
     * @param child
     */
    private void drawScrim(Canvas canvas, View child) {
        final int baseAlpha = (mScrimColor & 0xff000000) >>> 24;
        final int alpha = (int) (baseAlpha * mScrimOpacity);
        final int color = alpha << 24 | (mScrimColor & 0xffffff);
        canvas.clipRect(0, 0, child.getLeft(), getHeight());
        canvas.drawColor(color);
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
