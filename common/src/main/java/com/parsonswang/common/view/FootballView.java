package com.parsonswang.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.R;
import com.parsonswang.common.utils.UIUtils;

import timber.log.Timber;

/**
 * 自绘足球场view
 */
public class FootballView extends View {

    private static final int DIRECTION_VER = 0;
    private static final int DIRECTION_HOR = 1;

    private static final float FRAME_WIDTH_HEIGHT_RATIO = 1.6F;//球场宽高比
    private int mDirection;//球场方向(水平or垂直)
    private int mWidth;
    private int mHeight;

    private static final float FRAME_RESTRICT_HEIGHT_RATIO = 0.2F;//垂直方向禁区高度与球场高度比
    private static final float FRAME_RESTRICT_LEFT_RATIO = 0.17F;//垂直方向禁区左侧边距与球场宽度比
    private static final float FRAME_RESTRICT_WIDTH_RATIO = 0.66F;//垂直方向禁区宽度边距与球场宽度比

    //================垂直方向上上面的禁区参数=================
    private int mTopRestrictBottom;//禁区高度
    private int mTopRestrictRight;//禁区宽度+禁区左侧距离
    private int mTopRestrictLeft;//禁区左侧距离
    private int mTopRestrictTop;//进去顶部距离

    private int mBottomRestrictBottom;//禁区高度
    private int mBottomtrictRight;//禁区宽度
    private int mBottomRestrictLeft;//禁区左侧距离
    private int mBottomRestrictTop;//进去顶部距离

    private Paint mFramePaint;//用于绘制整个球场边界

    private void initFramePaint(Context context) {
        mFramePaint = new Paint();
        mFramePaint.setColor(context.getResources().getColor(android.R.color.white));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(UIUtils.dip2px(context, 2));
        mFramePaint.setAntiAlias(true);
    }

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

        initFramePaint(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //正规球场长度是宽度的1.6倍
        //如果高度没有明确指定(在ScrollView里子view为match-parent或wrap_content，其mode都表示未指定（UNSPECIFIED）)

        if (mDirection == DIRECTION_VER) {
            if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                mHeight = (int) (mWidth * FRAME_WIDTH_HEIGHT_RATIO);
                setMeasuredDimension(mWidth, mHeight);
            }
        } else if (mDirection == DIRECTION_HOR) {
            //如果高度为match_parent或wrap_content（未指定具体数值时）
            if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                mHeight = (int) (mWidth / FRAME_WIDTH_HEIGHT_RATIO);
                setMeasuredDimension(mWidth, mHeight);
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

    private void drawTopRestrict(Canvas canvas) {
        //大禁区
        mTopRestrictBottom = (int) (getMeasuredHeight() * FRAME_RESTRICT_HEIGHT_RATIO);
        mTopRestrictLeft = (int) (getMeasuredWidth() * FRAME_RESTRICT_LEFT_RATIO);
        mTopRestrictRight = (int) (getMeasuredWidth() * FRAME_RESTRICT_WIDTH_RATIO) + mTopRestrictLeft;
        mTopRestrictTop = 0;
        canvas.drawRect(mTopRestrictLeft, mTopRestrictTop, mTopRestrictRight, mTopRestrictBottom, mFramePaint);

        //小禁区

    }
    /**
     * 垂直布局
     * @param canvas
     */
    private void drawVec(Canvas canvas) {
        Timber.i("垂直布局");
        //1.绘制边线
        canvas.drawRect(0,0, getMeasuredWidth(), getMeasuredHeight(), mFramePaint);

        //2.绘制两个禁区
        drawTopRestrict(canvas);
    }

    /**
     * 水平布局
     * @param canvas
     */
    private void drawHor(Canvas canvas) {
        Timber.i("水平布局");
    }
}
