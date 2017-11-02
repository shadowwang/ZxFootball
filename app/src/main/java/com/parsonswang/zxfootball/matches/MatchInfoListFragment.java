package com.parsonswang.zxfootball.matches;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseFragment;
import com.parsonswang.common.utils.DateUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Date;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/23.
 */

public class MatchInfoListFragment extends BaseFragment implements MatchContract.IMatchInfoView {

    private static final String TAG = MatchInfoListFragment.class.getSimpleName();

    private static final String ARGUMENT_COMPETIONID = "competion";

    private MatchPresenter mMatchPresenter;
    private String mCompetionId;
    private int mCurrYear;
    private int mCurrMonth;//当前查询的月份

    private RecyclerView mRvMatchInfoList;
    private SmartRefreshLayout mRefreshLayout;


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

        mRefreshLayout = view.findViewById(R.id.mRefreshLayout);
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        //加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });

        mRvMatchInfoList = view.findViewById(R.id.mRvMatchInfoList);
        return view;
    }


    /**
     * 得到请求的时间区间
     * @return
     */
    private String getDateParams() {
        String currTimeString = DateUtils.date2String(DateUtils.getFirstDayOfMonth(new Date()));
        String params = currTimeString;
        params += "+至+";
        params += DateUtils.date2String(DateUtils.getLastDayOfMonth(new Date()));
        return params;
    }

    @Override
    public void showMatchInfoList(MatchesBean matchesBean) {
        Timber.i("---showMatchInfoList---" + matchesBean);
    }


    @Override
    protected void loadData() {
        Timber.i("---loadData---" + mCompetionId);
        mMatchPresenter.getMatchInfos(mCompetionId, getDateParams());
    }
}
