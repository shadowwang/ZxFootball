package com.parsonswang.zxfootball.matches.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by parsonswang on 2018/2/6.
 */

public class MatchDetailPageAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;

    public MatchDetailPageAdapter(FragmentManager fm) {
        super(fm);
        this.mTitles = new String[]{
                "比赛进程",
                "比赛阵容",
                "球员数据",
                "数据统计"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MatchTimeLineFragment();
            case 1:
                return new MatchLineupFragment();
            case 2:
                return new MatchPlayerStatFragment();
            case 3:
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
