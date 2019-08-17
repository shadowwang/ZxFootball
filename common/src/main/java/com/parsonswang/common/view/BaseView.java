package com.parsonswang.common.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by parsonswang on 2017/12/27.
 */

public abstract class BaseView<T> extends FrameLayout {

    public BaseView(@NonNull Context context) {
        super(context);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 绑定数据
     * @param t
     */
    protected abstract void bindData(T t);


}
