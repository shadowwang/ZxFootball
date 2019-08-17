package com.parsonswang.zxfootball.matches;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.view.PagerSlidingTabStrip;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.common.view.CommonHeaderTabAdapter;

import java.util.List;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class MatchesFragment extends Fragment implements MatchContract.IMatchView {

    private PagerSlidingTabStrip mTabs;
    private ViewPager mVpPager;
    private CommonHeaderTabAdapter mCommonHeaderTabAdapter;
    private MatchPresenter mMatchPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        mTabs = view.findViewById(R.id.tabs);
        mVpPager = view.findViewById(R.id.mVpPager);

        mCommonHeaderTabAdapter = new CommonHeaderTabAdapter(getFragmentManager());
        mVpPager.setAdapter(mCommonHeaderTabAdapter);

        mMatchPresenter = new MatchPresenter(this);
        mMatchPresenter.start();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTabs.setSelectedTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void showHeaderTabTitle(HeaderTabTitle headerTabTitle) {
        Timber.i("headerTabTitle|: " + headerTabTitle);
        List<HeaderTabTitle.TabInfo> dataBeanList = headerTabTitle.getData();
        mCommonHeaderTabAdapter.setDataBeanList(dataBeanList);
        mTabs.setViewPager(mVpPager);
    }
}
