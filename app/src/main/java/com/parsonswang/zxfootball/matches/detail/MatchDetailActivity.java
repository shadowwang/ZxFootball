package com.parsonswang.zxfootball.matches.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailActivity extends BaseActivity implements MatchContract.IMatchDetailView {

    private MatchPresenter mMatchPresenter;

    private TextView mTextView;

    public static void actionStart(Activity activity, String matchId) {
        Intent intent = new Intent(activity, MatchDetailActivity.class);
        intent.putExtra("matchId", matchId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        mMatchPresenter = new MatchPresenter(this);
        mMatchPresenter.getMatchDetail(getIntent().getStringExtra("matchId"));
        mTextView = findViewById(R.id.mTvTest);
    }

    @Override
    public void showMatchInfoHeader(MatchDetailHeaderInfoBean matchDetailHeaderInfoBean) {

    }

    @Override
    public void showMatchProcess(MatchSummary s) {

    }

    @Override
    public void showExceptionView() {

    }
}
