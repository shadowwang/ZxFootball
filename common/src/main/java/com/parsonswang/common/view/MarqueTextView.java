package com.parsonswang.common.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by wangchun on 2018/2/14.
 */

public class MarqueTextView extends androidx.appcompat.widget.AppCompatTextView {

    public MarqueTextView(Context context) {
        super(context);
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
