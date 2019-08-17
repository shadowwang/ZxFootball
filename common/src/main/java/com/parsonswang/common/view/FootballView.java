package com.parsonswang.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.parsonswang.common.R;
import com.parsonswang.common.utils.UIUtils;

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

    private static final float SMALL_RESTRICT_WITH_RESTRICT_WIDTH_RATIO = 0.45F;//大禁区与小禁区的宽度比
    private static final float SMALL_RESTRICT_HEIGHT_RESTRICT_HEIGHT_RATIO = 0.34F;//大禁区与小禁区的宽度比
    private static final float DIANPOINT_RESTRICT_HEIGHT_RATIO = 0.68F;//点球距离球门线与大禁区高度比
    private static final float MIDDLECIRCLE_FRAME_WIDTH_RATIO = 0.18F;//中圈半径与球场宽度比例

    //================垂直方向上上面的禁区参数=================
    private int mTopRestrictBottom;//禁区高度
    private int mTopRestrictRight;//禁区宽度+禁区左侧距离
    private int mTopRestrictLeft;//禁区左侧距离
    private int mTopRestrictTop;//禁区顶部距离

    private int mBottomRestrictBottom;//禁区高度
    private int mBottomRestrictRight;//禁区宽度
    private int mBottomRestrictLeft;//禁区左侧距离
    private int mBottomRestrictTop;//禁区顶部距离

    private int mTopSmallRestrictBottom;//小禁区高度
    private int mTopSmallRestrictRight;//小禁区宽度+禁区左侧距离
    private int mTopSmallRestrictLeft;//小禁区左侧距离
    private int mTopSmallRestrictTop;//小禁区顶部距离

    private int mBottomSmallRestrictBottom;//小禁区高度
    private int mBottomSmallRestrictRight;//小禁区宽度+禁区左侧距离
    private int mBottomSmallRestrictLeft;//小禁区左侧距离
    private int mBottomSmallRestrictTop;//小禁区顶部距离

    private Paint mFramePaint;//用于绘制整个球场边界
    private Paint mDotPaint;//画点的画笔

    private void initPaint(Context context) {
        mFramePaint = new Paint();
        mFramePaint.setColor(context.getResources().getColor(R.color.colorFootviewLine));
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(UIUtils.dip2px(context, 2));
        mFramePaint.setAntiAlias(true);

        mDotPaint = new Paint();
        mDotPaint.setColor(context.getResources().getColor(R.color.colorFootviewLine));
        mDotPaint.setStyle(Paint.Style.FILL);
        mDotPaint.setAntiAlias(true);
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

        initPaint(context);
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

    private int mRestrictWidth;//大禁区的宽
    private int mRestrictHeight;//大禁区的高
    private int mSmallRestrictWidth;//大禁区的宽
    private int mSmallRestrictHeight;//大禁区的高

    private void drawRestrict(Canvas canvas) {
        mRestrictWidth = (int) (getMeasuredWidth() * FRAME_RESTRICT_WIDTH_RATIO);
        mRestrictHeight = (int) (getMeasuredHeight() * FRAME_RESTRICT_HEIGHT_RATIO);
        mSmallRestrictWidth = (int) (mRestrictWidth * SMALL_RESTRICT_WITH_RESTRICT_WIDTH_RATIO);
        mSmallRestrictHeight = (int) (mRestrictHeight * SMALL_RESTRICT_HEIGHT_RESTRICT_HEIGHT_RATIO);

        //大禁区(上)
        mTopRestrictBottom = mRestrictHeight;
        mTopRestrictLeft = (int) (getMeasuredWidth() * FRAME_RESTRICT_LEFT_RATIO);
        mTopRestrictRight = mRestrictWidth + mTopRestrictLeft;
        mTopRestrictTop = 0;
        canvas.drawRect(mTopRestrictLeft, mTopRestrictTop, mTopRestrictRight, mTopRestrictBottom, mFramePaint);

        //大禁区(下)
        mBottomRestrictLeft = mTopRestrictLeft;
        mBottomRestrictTop = getMeasuredHeight() - mRestrictHeight;
        mBottomRestrictBottom = getMeasuredHeight();
        mBottomRestrictRight = mTopRestrictRight;
        canvas.drawRect(mBottomRestrictLeft, mBottomRestrictTop, mBottomRestrictRight, mBottomRestrictBottom, mFramePaint);


        //小禁区(上)
        mTopSmallRestrictLeft = mTopRestrictLeft + (mRestrictWidth - mSmallRestrictWidth) / 2;
        mTopSmallRestrictBottom = mSmallRestrictHeight;
        mTopSmallRestrictRight = mTopSmallRestrictLeft + mSmallRestrictWidth;
        mTopSmallRestrictTop = 0;
        canvas.drawRect(mTopSmallRestrictLeft, mTopSmallRestrictTop, mTopSmallRestrictRight, mTopSmallRestrictBottom, mFramePaint);

        //小禁区(下)
        mBottomSmallRestrictLeft = mTopSmallRestrictLeft;
        mBottomSmallRestrictTop = getMeasuredHeight() - mSmallRestrictHeight;
        mBottomSmallRestrictBottom = getMeasuredHeight();
        mBottomSmallRestrictRight = mTopSmallRestrictRight;
        canvas.drawRect(mBottomSmallRestrictLeft, mBottomSmallRestrictTop, mBottomSmallRestrictRight, mBottomSmallRestrictBottom, mFramePaint);
    }

    /**
     * 绘制点球点
     * @param canvas
     */
    private void drawDot(Canvas canvas) {
        //上半部禁区点球点
        int mDianPointLeft = getMeasuredWidth() / 2;
        int mDianPointTop = (int) (mRestrictHeight * DIANPOINT_RESTRICT_HEIGHT_RATIO);
        canvas.drawCircle(mDianPointLeft, mDianPointTop, UIUtils.dip2px(getContext(), 2), mDotPaint);

        //下半部禁区点球点
        int mBottomDianPointTop = getMeasuredHeight() - mDianPointTop;
        canvas.drawCircle(mDianPointLeft, mBottomDianPointTop, UIUtils.dip2px(getContext(), 2), mDotPaint);

        //中圈点
        int middleDotX = getMeasuredWidth() / 2;
        int middleDotY = getMeasuredHeight() / 2;
        canvas.drawCircle(middleDotX, middleDotY, UIUtils.dip2px(getContext(), 3), mDotPaint);
    }

    /**
     * 画中线
     * @param canvas
     */
    private void drawMiddleLine(Canvas canvas) {
        int middleLineStartX = 0;
        int middleLineStartY = getMeasuredHeight() / 2;

        int middleLineEndX = getMeasuredWidth();
        int middleLineEndY = getMeasuredHeight() / 2;
        canvas.drawLine(middleLineStartX, middleLineStartY, middleLineEndX, middleLineEndY, mFramePaint);
    }

    /**
     * 画中圈
     * @param canvas
     */
    private void drawMiddleCircle(Canvas canvas) {
        int middleCircleX = getMeasuredWidth() / 2;
        int middleCircleY = getMeasuredHeight() / 2;
        int radius = (int) (getMeasuredWidth() * MIDDLECIRCLE_FRAME_WIDTH_RATIO);
        canvas.drawCircle(middleCircleX, middleCircleY, radius, mFramePaint);
    }

    /**
     * 绘制四个角旗区的弧
     */
    private void drawCornerArc(Canvas canvas) {
        final int size = UIUtils.dip2px(getContext(), 20);
        //左上方的角球区
        RectF leftTopRect = new RectF();
        leftTopRect.left = -size / 2;
        leftTopRect.top = -size / 2;
        leftTopRect.bottom = size / 2;
        leftTopRect.right = size / 2;
        canvas.drawArc(leftTopRect, 0,90,false, mFramePaint);

        //右上方角球区
        RectF rightTopRect = new RectF();
        rightTopRect.left = getMeasuredWidth() - size / 2;
        rightTopRect.top = -size / 2;
        rightTopRect.bottom = size / 2;
        rightTopRect.right = getMeasuredWidth() + size / 2;
        canvas.drawArc(rightTopRect, -180,-270,false, mFramePaint);

        //左下方角球区
        RectF leftBottomRect = new RectF();
        leftBottomRect.left = -size / 2;
        leftBottomRect.top = getMeasuredHeight() - size / 2;
        leftBottomRect.bottom = getMeasuredHeight() + size / 2;
        leftBottomRect.right = size / 2;
        canvas.drawArc(leftBottomRect, -90,180,false, mFramePaint);

        //右下方角球区
        RectF rightBottomRect = new RectF();
        rightBottomRect.left = getMeasuredWidth() - size / 2;
        rightBottomRect.top = getMeasuredHeight() - size / 2;
        rightBottomRect.bottom = getMeasuredHeight() + size / 2;
        rightBottomRect.right = getMeasuredWidth() + size / 2;
        canvas.drawArc(rightBottomRect, -90,-270,false, mFramePaint);

    }

    /**
     * 绘制禁区顶弧
     * @param canvas
     */
    private void drawRestrictArc(Canvas canvas) {
        //禁区弧长度与小禁区宽度之差 
        int diff = (int) ((mSmallRestrictWidth * 0.15) / 2);
        int restrictArcHeight = (int) ((int) (mSmallRestrictHeight * 0.8) / 1.2);

        //顶部禁区弧
        RectF restrictTopArcRect = new RectF();
        restrictTopArcRect.left = mTopSmallRestrictLeft + diff;
        restrictTopArcRect.top = mRestrictHeight - restrictArcHeight;
        restrictTopArcRect.bottom = mRestrictHeight + restrictArcHeight;
        restrictTopArcRect.right = mTopSmallRestrictRight - diff;
        canvas.drawArc(restrictTopArcRect, -180,-180,false, mFramePaint);

        //底部禁区弧
        RectF restrictBottomArcRect = new RectF();
        restrictBottomArcRect.left = restrictTopArcRect.left;
        restrictBottomArcRect.top = mBottomRestrictTop - restrictArcHeight;
        restrictBottomArcRect.bottom = mBottomRestrictTop + restrictArcHeight;
        restrictBottomArcRect.right = restrictTopArcRect.right;
        canvas.drawArc(restrictBottomArcRect, -180,180,false, mFramePaint);

    }

    /**
     * 垂直布局
     * @param canvas
     */
    private void drawVec(Canvas canvas) {
        //1.绘制整个球场边线
        canvas.drawRect(0,0, getMeasuredWidth(), getMeasuredHeight(), mFramePaint);

        //2.绘制禁区
        drawRestrict(canvas);

        //3.绘制点球点
        drawDot(canvas);

        //4.画中线
        drawMiddleLine(canvas);

        //5.画中圈
        drawMiddleCircle(canvas);

        //6.绘制角球区弧
        drawCornerArc(canvas);

        //7.绘制两个禁区顶弧
        drawRestrictArc(canvas);
    }

    /**
     * 水平布局
     * @param canvas
     */
    private void drawHor(Canvas canvas) {
    }
}
