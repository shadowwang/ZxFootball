package com.parsonswang.zxfootball.matches;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseFragment;
import com.parsonswang.common.utils.DateUtils;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.CommonRecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
    private MatchInfoAdapter mMatchInfoAdapter;


    public static MatchInfoListFragment newInstance(HeaderTabTitle.TabInfo tabInfo) {
        MatchInfoListFragment matchInfoListFragment = new MatchInfoListFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_COMPETIONID, String.valueOf(tabInfo.getId()));
        matchInfoListFragment.setArguments(bundle);

        return matchInfoListFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Timber.i("--onViewCreated--");
        mRefreshLayout = view.findViewById(R.id.mRefreshLayout);
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mMatchPresenter.getMatchInfos(mCompetionId, getDateParams());
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });

        mRvMatchInfoList = view.findViewById(R.id.mRvMatchInfoList);
        mRvMatchInfoList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        mRvMatchInfoList.addItemDecoration(new CommonRecyclerViewDivider(
                getContext(),
                LinearLayoutManager.VERTICAL,
                UIUtils.dip2px(getContext(), 14.4F),
                Color.parseColor("#232C30")));

        mMatchInfoAdapter = new MatchInfoAdapter();
        mRvMatchInfoList.setAdapter(mMatchInfoAdapter);

        mMatchPresenter = new MatchPresenter(this);
        mCompetionId = getArguments().getString(ARGUMENT_COMPETIONID);
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("--onCreateView--");
        return inflater.inflate(R.layout.fragment_matchinfo_list, container, false);
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

        final List<MatchesBean.MatchInfo> matchInfos = matchesBean.getDatas();
        //先分为已比赛和未比赛的
        List<MatchesBean.MatchInfo> hasMatchedList = new ArrayList<>();
//        List<MatchesBean.MatchInfo> noMatchedList = new ArrayList<>();

        for (MatchesBean.MatchInfo matchInfo : matchInfos) {
            if (matchInfo.isIsFinish()) {
                hasMatchedList.add(matchInfo);
            }
        }


        Collections.sort(hasMatchedList, new Comparator<MatchesBean.MatchInfo>() {
            @Override
            public int compare(MatchesBean.MatchInfo matchInfo1, MatchesBean.MatchInfo matchInfo2) {
                //降序排列
                return DateUtils.compareDate(matchInfo2.getMatchDate(), matchInfo1.getMatchDate());
            }
        });

//        Collections.sort(noMatchedList, new Comparator<MatchesBean.MatchInfo>() {
//            @Override
//            public int compare(MatchesBean.MatchInfo matchInfo1, MatchesBean.MatchInfo matchInfo2) {
//                return DateUtils.compareDate(matchInfo2.getMatchDate(), matchInfo1.getMatchDate());
//            }
//        });

        matchInfos.clear();
        matchInfos.addAll(hasMatchedList);
//        matchInfos.addAll(noMatchedList);
        Timber.i("---showMatchInfoList---" + matchInfos);
        mMatchInfoAdapter.addAll(matchInfos);
        mRefreshLayout.finishRefresh();
    }


    @Override
    protected void loadData() {
        Timber.i("---loadData---" + mCompetionId);
        mRefreshLayout.autoRefresh();
    }
}
