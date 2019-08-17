package com.parsonswang.zxfootball.matches;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.common.utils.DateUtils;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.common.view.pinheader.PinnedHeaderItemDecoration;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.CommonRecyclerViewDivider;
import com.parsonswang.zxfootball.common.view.MatchScoreInfoView;
import com.parsonswang.zxfootball.matches.detail.MatchDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/23.
 */

public class MatchInfoListFragment extends BaseLazyLoadFragment implements MatchContract.IMatchInfoView,
        MatchScoreInfoView.OnItemClickListener<MatchesBean.MatchInfo> {

    private static final String TAG = MatchInfoListFragment.class.getSimpleName();

    private static final String ARGUMENT_COMPETIONID = "competion";

    private MatchPresenter mMatchPresenter;
    private String mCompetionId;
    private int mRollbackMonth;//回退到前多少月

    private RecyclerView mRvMatchInfoList;
    private SmartRefreshLayout mRefreshLayout;
    private MatchInfoAdapter mMatchInfoAdapter;

    private int mOffset;


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
                mRollbackMonth = 0;
                mOffset = 0;
                mMatchPresenter.getMatchInfos(mCompetionId, getSpecifyDateParams());
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mRollbackMonth ++;
                mOffset ++;
                mMatchPresenter.getMatchInfos(mCompetionId, getSpecifyDateParams());
            }
        });

        mRvMatchInfoList = view.findViewById(R.id.mRvMatchInfoList);
        mRvMatchInfoList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        mRvMatchInfoList.addItemDecoration(new CommonRecyclerViewDivider(
                getContext(),
                LinearLayoutManager.VERTICAL,
                UIUtils.dip2px(getContext(), 14.4F),
                Color.parseColor("#232C30")));

        mMatchInfoAdapter = new MatchInfoAdapter(getContext());
        mRvMatchInfoList.setAdapter(mMatchInfoAdapter);
        mMatchInfoAdapter.setOnItemClickListener(this);

        mRvMatchInfoList.addItemDecoration(PinnedHeaderItemDecoration.builder().adapterProvider(mMatchInfoAdapter).build());

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
    private String getHeaderDateStr() {
        String currTimeString = DateUtils.date2String(DateUtils.getSomeMonthOfFirstDay(new Date(), mRollbackMonth), new SimpleDateFormat("yyyy-MM"));

        return currTimeString;
    }

    private String getSpecifyDateParams() {
        String str = DateUtils.date2String(DateUtils.getSomeMonthOfFirstDay(new Date(), mRollbackMonth));
        Timber.e(str);

        String str1 = DateUtils.date2String(DateUtils.getSomeMonthOfLastDay(new Date(), mRollbackMonth));
        Timber.e(str1);

        return  str + "+至+" + str1;
    }


    private void getNextMatchInfo() {
        mRollbackMonth ++;
        mMatchPresenter.getMatchInfos(mCompetionId, getSpecifyDateParams());
    }

    @Override
    public void showMatchInfoList(MatchesBean matchesBean) {

        if (matchesBean == null) {
            return;
        }

        final List<MatchesBean.MatchInfo> matchInfos = matchesBean.getDatas();
        if (Integer.parseInt(mCompetionId) != 21 && matchInfos.isEmpty()) {
            getNextMatchInfo();
            return;
        }


        //先分为已比赛和未比赛的
        final List<MatchesBean.MatchInfo> hasMatchedList = new ArrayList<>();
//        List<MatchesBean.MatchInfo> noMatchedList = new ArrayList<>();

        //增加比赛日期头部
        if (!matchInfos.isEmpty()) {
            MatchesBean.MatchInfo matchInfoHeader = new MatchesBean.MatchInfo();
            matchInfoHeader.type = MatchesBean.MatchInfo.TYPE_TITLE;
            matchInfoHeader.setMatchDate(getHeaderDateStr());
            hasMatchedList.add(matchInfoHeader);
        }


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



        matchInfos.clear();
        matchInfos.addAll(hasMatchedList);
        Timber.i("---showMatchInfoList---" + matchInfos);

        if (mOffset == 0) {
            mMatchInfoAdapter.clearData();
            mRefreshLayout.finishRefresh();
        } else {
            mRefreshLayout.finishLoadmore();
        }

        mMatchInfoAdapter.addAll(matchInfos);

        //fix:每月初赛事很少情况下做个补偿处理,以免体验不好(世界杯这种四年一次的就算了)
        if (Integer.parseInt(mCompetionId) != 21 && hasMatchedList.size() < 10) {
            mRollbackMonth ++;
            mMatchPresenter.getMatchInfos(mCompetionId, getSpecifyDateParams());
        }
    }


    @Override
    protected void loadData() {
        Timber.i("---loadData---" + mCompetionId);
        mOffset = 0;
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onItemClick(MatchesBean.MatchInfo matchInfo) {
        MatchDetailActivity.actionStart(getActivity(), String.valueOf(matchInfo.getId()));
    }

}
