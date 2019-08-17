/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsonswang.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parsonswang.common.R;

import java.util.List;
import java.util.Locale;


public class PagerSlidingTabStrip extends HorizontalScrollView {

    //只有当tabPadding为0时3个属性才生效 分别为最左边距, 中间间隔, 最右间距
    private int mTabFirstItemPaddingLeft = 0;
    private int mTabLastItemPaddingRight = 0;
    private int mTabPaddingCenter = 0;

    private TabClickListener mTabClickListenr;
    private float selectedTextSize = 0;

    public interface IconTabProvider {
        public int getPageIconResId(int position);
    }

    // @formatter:off
    private static final int[] ATTRS = new int[]{android.R.attr.textSize, android.R.attr.textColor};
    // @formatter:on

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private final PageListener pageListener = new PageListener();
    public OnPageChangeListener delegatePageListener;

    private LinearLayout tabsContainer;
    private ViewPager pager;

    private int tabCount;

    private int currentPosition = 0;
    private int selectedPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;

    private int indicatorColor = 0xFFFDD52B;
    private int underlineColor = 0x1A000000;
    private int dividerColor = 0x00000000;

    private boolean shouldExpand = false;
    private boolean textAllCaps = true;

    private int scrollOffset = 52;
    private int indicatorHeight = 5;
    private int indicatorPadding = 0;
    private int underlineHeight = 0;
    private int dividerPadding = 12;
    private int tabPadding = 24;
    private int dividerWidth = 1;

    private int tabTextSize = 16;
    private int tabTextColor = 0xFF999999;
    private int selectedTabTextColor = 0xFF333333;
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = Typeface.NORMAL;

    private int lastScrollX = 0;

    private int tabBackgroundResId = R.drawable.background_tab;

    private Locale locale;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
        indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
        indicatorPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorPadding, dm);
        underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
        dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
        tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
        dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
        tabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabTextSize, dm);

        // get system attrs (android:textSize and android:textColor)

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
        tabTextColor = a.getColor(1, tabTextColor);

        a.recycle();

        // get custom attrs

        a = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        indicatorColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, indicatorColor);
        underlineColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, dividerColor);
        tabTextColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsTextColor, tabTextColor);

        indicatorHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight,
                indicatorHeight);
        indicatorPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorPadding,
                indicatorPadding);
        underlineHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight,
                underlineHeight);
        dividerPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding,
                dividerPadding);
        tabPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight,
                tabPadding);
        tabBackgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground,
                tabBackgroundResId);
        shouldExpand = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, shouldExpand);
        scrollOffset = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset,
                scrollOffset);
        textAllCaps = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Style.FILL);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
    }

    /**
     * 拓展方法，支持自定义Tab
     *
     * @param pager
     * @param viewList
     */
    public void setViewPager(ViewPager pager, List<View> viewList) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        if (pager.getAdapter().getCount() != viewList.size()) {
            throw new IllegalStateException("size of viewPager must be equal with viewList's.");
        }

        pager.setOnPageChangeListener(pageListener);

        notifyDataSetChanged(viewList);
    }


    public void setViewPager(ViewPager pager) {
        try {
            this.pager = pager;

            if (pager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }

            pager.setOnPageChangeListener(pageListener);

            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void notifyDataSetChanged() {

        tabsContainer.removeAllViews();
        if (pager == null) {
            return;
        }
        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount; i++) {

            if (pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
            } else {

                if (pager.getAdapter().getPageTitle(i) != null) {
                    addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
                }
            }
        }

        updateTabStyles();

        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                currentPosition = pager.getCurrentItem();
                scrollToChild(currentPosition, 0);
            }
        });

    }

    public void updateTabs(List<View> viewList) {
        notifyDataSetChanged(viewList);
    }

    /**
     * 拓展方法，支持自定义的Tab
     *
     * @param viewList
     */
    private void notifyDataSetChanged(List<View> viewList) {
        tabsContainer.removeAllViews();

        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount && i < viewList.size(); i++) {

            addTab(i, viewList.get(i));
        }

        updateTabStyles();

        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                currentPosition = pager.getCurrentItem();
                scrollToChild(currentPosition, 0);
            }
        });
    }

    private void addTextTab(final int position, String title) {

        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();
        addTab(position, tab);
    }

    private void addIconTab(final int position, int resId) {

        ImageButton tab = new ImageButton(getContext());
        tab.setImageResource(resId);

        addTab(position, tab);

    }


    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(position, false);
                if (mTabClickListenr != null) {
                    mTabClickListenr.onClick(v, position);
                }
            }
        });
        int paddingLeft = 0;
        int paddingRight = 0;
        if (tabPadding == 0) {
            if (position == 0) {
                paddingLeft = mTabFirstItemPaddingLeft;
                paddingRight = mTabPaddingCenter;
            } else if (position == pager.getAdapter().getCount() - 1) {
                paddingLeft = 0;
                paddingRight = mTabLastItemPaddingRight;
            } else {
                paddingLeft = 0;
                paddingRight = mTabPaddingCenter;
            }
        } else {
            paddingLeft = tabPadding;
            paddingRight = tabPadding;
        }


        tab.setPadding(paddingLeft, 0, paddingRight, 0);
        try {
            tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }


    public void setFirstTabLeftPadding(int firstLeftPadding) {
        mTabFirstItemPaddingLeft = firstLeftPadding;
    }

    public void setLastTabPaddingRight(int lastRightPadding) {
        mTabLastItemPaddingRight = lastRightPadding;
    }

    public void setIndicatorInterval(int interval) {
        mTabPaddingCenter = interval;
    }

    private void updateTabStyles() {

        for (int i = 0; i < tabCount; i++) {

            View v = tabsContainer.getChildAt(i);
            if (v == null) {
                return;
            }
            if (v == null) {
                //极端情况下这个v会为空
                continue;
            }

            v.setBackgroundResource(tabBackgroundResId);

            if (v instanceof TextView) {

                TextView tab = (TextView) v;
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                tab.setTextColor(tabTextColor);
                tab.setBackgroundColor(Color.TRANSPARENT);

                // setAllCaps() is only available from API 14, so the upper case
                // is made manually if we are on a
                // pre-ICS-build
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(locale));
                    }
                }
                setSelectStyles(i, tab);
            } else if (v instanceof ViewGroup) {
                for (int j = 0; j < ((ViewGroup) v).getChildCount(); j++) {
                    View child = ((ViewGroup) v).getChildAt(j);
                    if (child instanceof TextView) {

                        TextView tab = (TextView) child;
                        //tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                        //tab.setTypeface(tabTypeface, tabTypefaceStyle);
                        tab.setTextColor(tabTextColor);
                        //tab.setBackgroundColor(Color.TRANSPARENT);
                        // setAllCaps() is only available from API 14, so the upper case
                        // is made manually if we are on a
                        // pre-ICS-build
                        if (textAllCaps) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                tab.setAllCaps(true);
                            } else {
                                tab.setText(tab.getText().toString().toUpperCase(locale));
                            }
                        }
                        setSelectStyles(i, tab);
                    }
                }
            }
        }

    }

    private void setSelectStyles(int i, TextView tab) {
        if (i == selectedPosition) {
            tab.setTextColor(selectedTabTextColor);
            if (selectedTextSize > 0.1) {
                tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, selectedTextSize);
            }
        }
    }

    private void enableSelectFold(){

    }

    private void scrollToChild(int position, int offset) {

        if (tabCount == 0) {
            return;
        }

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }

    }

    private Paint roundRecPaint = new Paint();

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || tabCount == 0) {
            return;
        }

        final int height = getHeight();

        // draw underline
        rectPaint.setColor(underlineColor);
        canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);
        // canvas.drawRoundRect(0, height - underlineHeight, tabsContainer.getWidth(), height,0,0, rectPaint);
        // draw indicator line
//        rectPaint.setColor(indicatorColor);

        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();

        // if there is an offset, start interpolating left and right coordinates
        // between current and next tab
        if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

            View nextTab = tabsContainer.getChildAt(currentPosition + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();
            if (currentPosition == 0 && tabPadding == 0) {
                lineLeft = (currentPositionOffset * (nextTabLeft - mTabFirstItemPaddingLeft) + (1f - currentPositionOffset) * lineLeft);
            } else {
                lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
            }
            if (currentPosition == tabCount - 2 && tabPadding == 0) {
                lineRight = (currentPositionOffset * (nextTabRight + mTabPaddingCenter - mTabLastItemPaddingRight) + (1f - currentPositionOffset) * (lineRight));
            } else {
                lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * (lineRight));
            }

        }


        //canvas.drawRect(lineLeft+tabPadding, height - indicatorHeight, lineRight-tabPadding, height, rectPaint);

        //画圆角矩形

        roundRecPaint.setColor(indicatorColor);

        int paddingLeft, paddingRight;
        if (tabPadding == 0) {
            if (currentPosition == 0) {
                paddingLeft = mTabFirstItemPaddingLeft;
                paddingRight = mTabPaddingCenter;
            } else if (currentPosition == pager.getAdapter().getCount() - 1) {
                paddingLeft = 0;
                paddingRight = mTabLastItemPaddingRight;
            } else {
                paddingLeft = 0;
                paddingRight = mTabPaddingCenter;
            }

        } else {
            paddingLeft = tabPadding;
            paddingRight = tabPadding;
        }

        RectF re3 = new RectF(lineLeft + paddingLeft + indicatorPadding, height - indicatorHeight, lineRight - paddingRight - indicatorPadding, height);
        canvas.drawRoundRect(re3, 2, 10, roundRecPaint);

        // draw divider
        dividerPaint.setColor(dividerColor);
        for (int i = 0; i < tabCount - 1; i++) {
            View tab = tabsContainer.getChildAt(i);
            canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding,
                    dividerPaint);
        }
    }

    private class PageListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (tabsContainer.getChildCount() == 0) return;

            currentPosition = position;
            currentPositionOffset = positionOffset;

            smoothScrollTo(calculateScrollXForTab(position, positionOffset), 0);
            invalidate();

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
            }

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            selectedPosition = position;
            updateTabStyles();
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
        }

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = getResources().getColor(resId);
        invalidate();
    }

    private int calculateScrollXForTab(int position, float positionOffset) {
        View selectedChild = tabsContainer.getChildAt(position);
        View nextChild = position + 1 < this.tabsContainer.getChildCount() ? this.tabsContainer.getChildAt(position + 1) : null;
        int selectedWidth = selectedChild != null ? selectedChild.getWidth() : 0;
        int nextWidth = nextChild != null ? nextChild.getWidth() : 0;
        return selectedChild.getLeft() + (int) ((float) (selectedWidth + nextWidth) * positionOffset * 0.5F) + selectedChild.getWidth() / 2 - this.getWidth() / 2;
    }


    public int getIndicatorColor() {
        return this.indicatorColor;
    }


    public int getIndicatorPadding() {
        return indicatorPadding;
    }

    public void setIndicatorPadding(int indicatorPadding) {
        this.indicatorPadding = indicatorPadding;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        invalidate();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        notifyDataSetChanged();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }

    public boolean isTextAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public int getTextSize() {
        return tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return tabTextColor;
    }

    public void setSelectedTextColor(int textColor) {
        this.selectedTabTextColor = textColor;
        updateTabStyles();
    }

    public void setSelectedTextSize(float dpSize) {
        this.selectedTextSize = dpSize;
        updateTabStyles();
    }

    public void setSelectedTextColorResource(int resId) {
        this.selectedTabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getSelectedTextColor() {
        return selectedTabTextColor;
    }

    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
        updateTabStyles();
    }

    public int getTabBackground() {
        return tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPadding = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return tabPadding;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    static class SavedState extends BaseSavedState {
        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    /**
     * 监听tab点击事件
     *
     * @param tabListener
     */
    public void setOnTabClickListener(TabClickListener tabListener) {
        mTabClickListenr = tabListener;
    }

    public interface TabClickListener {
        void onClick(View v, int currentPosition);
    }
}
