package com.parsonswang.zxfootball.matches.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.common.view.PagerSlidingTabStrip;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailActivity extends BaseActivity {

    private PagerSlidingTabStrip mTabs;
    private MatchDetailHerderFragment mMatchDetailHerderFragment;
    private ViewPager mViewPager;
    private MatchDetailPageAdapter mMatchDetailPageAdapter;

    public static void actionStart(Activity activity, String matchId) {
        Intent intent = new Intent(activity, MatchDetailActivity.class);
        intent.putExtra("matchId", matchId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        mViewPager = findViewById(R.id.vp_detail);
        mTabs = findViewById(R.id.tabs);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        mMatchDetailPageAdapter = new MatchDetailPageAdapter(fragmentManager);
        mViewPager.setAdapter(mMatchDetailPageAdapter);
        mTabs.setViewPager(mViewPager);
        mTabs.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("matchId") && fragmentManager != null) {
            final String matchId = getIntent().getStringExtra("matchId");
            if (!StringUtils.isEmptyString(matchId)) {
                mMatchDetailHerderFragment = MatchDetailHerderFragment.newInstance(matchId);
            }

            if (mMatchDetailHerderFragment != null) {
                fragmentManager.beginTransaction().
                        add(R.id.fl_matchinfo_header, mMatchDetailHerderFragment).
                        commitAllowingStateLoss();
            }
        }
    }

}
