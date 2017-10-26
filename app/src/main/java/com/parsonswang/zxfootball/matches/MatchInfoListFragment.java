package com.parsonswang.zxfootball.matches;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseFragment;
import com.parsonswang.common.utils.DateUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;

import java.util.Date;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/23.
 */

public class MatchInfoListFragment extends BaseFragment implements MatchContract.IMatchInfoView {

    private static final String ARGUMENT_COMPETIONID = "competion";

    private MatchPresenter mMatchPresenter;
    private String mCompetionId;
    private boolean isVisible;

    public static MatchInfoListFragment newInstance(HeaderTabTitle.TabInfo tabInfo) {
        MatchInfoListFragment matchInfoListFragment = new MatchInfoListFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_COMPETIONID, String.valueOf(tabInfo.getId()));
        matchInfoListFragment.setArguments(bundle);

        return matchInfoListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchinfo_list, container, false);

        mMatchPresenter = new MatchPresenter(this);
        mCompetionId = getArguments().getString(ARGUMENT_COMPETIONID);

        return view;
    }


    private String getCurrentTimeStr() {
        Date date = new Date();
        int year = DateUtils.getYear(date);
        int month = DateUtils.getMonth(date) + 1;
        String dateStr = year + "." + month + ".01";
        return dateStr;
    }

    private String getDateParams() {
        String currTimeString = getCurrentTimeStr();
        String params = currTimeString;
        params += "+至+";
        params += DateUtils.date2String(new Date());
        return params;
    }

    @Override
    public void showMatchInfoList(MatchesBean matchesBean) {
        Timber.i("---showMatchInfoList---");
    }

    @Override
    protected void onFragmentFirstVisible(boolean isVisible) {

    }
}
