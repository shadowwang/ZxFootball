package com.parsonswang.zxfootball.matches.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.common.view.PagerSlidingTabStrip;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.common.view.CommonActionBar;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailActivity extends BaseActivity {

    private CommonActionBar mCommonActionBar;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        mViewPager = findViewById(R.id.vp_detail);
        mTabs = findViewById(R.id.tabs);
        mCommonActionBar = findViewById(R.id.mActionBar);
        mCommonActionBar.hideRightArrow();
        mCommonActionBar.showLeftArrow();
        mCommonActionBar.setLeftArrowClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final FragmentManager fragmentManager = getSupportFragmentManager();

        mMatchDetailPageAdapter = new MatchDetailPageAdapter(fragmentManager);
        mViewPager.setAdapter(mMatchDetailPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                swipeLayoutHelper.getSwipeBackLayout().setEnable(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setViewPager(mViewPager);
        mTabs.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("matchId") && fragmentManager != null) {
            final String matchId = getIntent().getStringExtra("matchId");
            if (!StringUtils.isEmptyString(matchId)) {
                mMatchDetailHerderFragment = MatchDetailHerderFragment.newInstance(matchId);
                if (mMatchDetailPageAdapter != null) {
                    mMatchDetailPageAdapter.setMathcId(matchId);
                }
            }

            if (mMatchDetailHerderFragment != null) {
                fragmentManager.beginTransaction().
                        add(R.id.fl_matchinfo_header, mMatchDetailHerderFragment).
                        commitAllowingStateLoss();
            }
        }
    }

}
