package com.parsonswang.zxfootball.common.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.matches.MatchInfoListFragment;

import java.util.List;

/**
 * Created by parsonswang on 2017/10/23.
 */

public class CommonHeaderTabAdapter extends FragmentPagerAdapter {

    private List<HeaderTabTitle.TabInfo> mDataBeanList;


    public void setDataBeanList(List<HeaderTabTitle.TabInfo> dataBeens) {
        mDataBeanList = dataBeens;
        notifyDataSetChanged();
    }

    public CommonHeaderTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MatchInfoListFragment.newInstance(mDataBeanList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataBeanList.get(position).getName();
    }

    @Override
    public int getCount() {
        return mDataBeanList == null ? 0 : mDataBeanList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
