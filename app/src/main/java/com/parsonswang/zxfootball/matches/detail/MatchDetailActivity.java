package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.zxfootball.R;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        findViewById(R.id.mTvTest);
    }
}
