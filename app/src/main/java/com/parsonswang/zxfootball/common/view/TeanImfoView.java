package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.parsonswang.zxfootball.R;

/**
 * Created by parsonswang on 2017/11/6.
 */

public class TeanImfoView extends FrameLayout {

    public TeanImfoView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TeanImfoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_team_info_view, null, false);
    }

    public void setInfo(String teamId, String teamName) {

    }
}
