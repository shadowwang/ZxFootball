package com.parsonswang.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.parsonswang.common.R;

import timber.log.Timber;

/**
 * 自绘足球场view
 */
public class FootballView extends View {

    private static final int DIRECTION_VER = 0;
    private static final int DIRECTION_HOR = 1;

    private int mDirection;//球场方向(水平or垂直)

    private int mHeight;

    public void setDirection(int direction) {
        this.mDirection = direction;
        requestLayout();
    }

    public FootballView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FootballView);

        final int direnction = ta.getInteger(R.styleable.FootballView_fbvDirection, DIRECTION_HOR);
        setDirection(direnction);

        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //正规球场长度是宽度的1.6倍
        //如果高度为match_parent或wrap_content
        if (mDirection == DIRECTION_VER) {
            if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.UNSPECIFIED) {
                final int currentWidth = MeasureSpec.getSize(widthMeasureSpec);
                mHeight = (int) (currentWidth * 1.6);
                setMeasuredDimension(currentWidth, mHeight);
            }
        } else if (mDirection == DIRECTION_HOR) {
            //如果高度为match_parent或wrap_content
            if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.UNSPECIFIED) {
                final int currentWidth = MeasureSpec.getSize(widthMeasureSpec);
                mHeight = (int) (currentWidth / 1.6);
                setMeasuredDimension(currentWidth, mHeight);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDirection == DIRECTION_HOR) {
            drawHor(canvas);
        } else if (mDirection == DIRECTION_VER) {
            drawVec(canvas);
        }
    }

    /**
     * 垂直布局
     * @param canvas
     */
    private void drawVec(Canvas canvas) {
        Timber.i("垂直布局");
    }

    /**
     * 水平布局
     * @param canvas
     */
    private void drawHor(Canvas canvas) {
        Timber.i("水平布局");
    }
}
