package com.parsonswang.zxfootball.matches.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by parsonswang on 2018/2/6.
 */

public class MatchDetailPageAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    private String mMatchId;

    public void setMathcId(String matchId) {
        this.mMatchId = matchId;
        notifyDataSetChanged();
    }

    public MatchDetailPageAdapter(FragmentManager fm) {
        super(fm);
        this.mTitles = new String[]{
                "比赛信息",
                "球员数据",
                "比赛统计"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MatchTimeLineFragment.newInstance(mMatchId);
            case 1:
                return MatchPlayerStatFragment.newInstance(mMatchId);
            case 2:
                return new MatchStatFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
