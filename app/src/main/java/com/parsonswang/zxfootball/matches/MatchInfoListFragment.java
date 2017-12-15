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
import com.parsonswang.common.view.pinheader.PinnedHeaderItemDecoration;
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

        mMatchInfoAdapter = new MatchInfoAdapter();
        mRvMatchInfoList.setAdapter(mMatchInfoAdapter);

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
        String currTimeString = DateUtils.date2String(DateUtils.getSomeMonthOfFirstDay(new Date(), mRollbackMonth));
        String params = currTimeString;
        params += "——";
        params += DateUtils.date2String(DateUtils.getSomeMonthOfLastDay(new Date(), mRollbackMonth));
        return params;
    }

    private String getSpecifyDateParams() {
        String str = DateUtils.date2String(DateUtils.getSomeMonthOfFirstDay(new Date(), mRollbackMonth));
        Timber.e(str);

        String str1 = DateUtils.date2String(DateUtils.getSomeMonthOfLastDay(new Date(), mRollbackMonth));
        Timber.e(str1);

        return  str + "+至+" + str1;
    }


    @Override
    public void showMatchInfoList(MatchesBean matchesBean) {

        final List<MatchesBean.MatchInfo> matchInfos = matchesBean.getDatas();
        if (matchInfos.isEmpty()) {
            mRollbackMonth ++;

            mMatchPresenter.getMatchInfos(mCompetionId, getSpecifyDateParams());
            return;
        }

        if (matchInfos.isEmpty()) {
            return;
        }
        //先分为已比赛和未比赛的
        List<MatchesBean.MatchInfo> hasMatchedList = new ArrayList<>();
//        List<MatchesBean.MatchInfo> noMatchedList = new ArrayList<>();

        //增加比赛日期头部
        MatchesBean.MatchInfo matchInfoHeader = new MatchesBean.MatchInfo();
        matchInfoHeader.type = MatchesBean.MatchInfo.TYPE_TITLE;
        matchInfoHeader.setMatchDate(getHeaderDateStr());
        hasMatchedList.add(matchInfoHeader);

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
        if (mOffset == 0) {
            mRefreshLayout.finishRefresh();
        } else {
            mRefreshLayout.finishLoadmore();
        }
    }


    @Override
    protected void loadData() {
        Timber.i("---loadData---" + mCompetionId);
        mOffset = 0;
        mRefreshLayout.autoRefresh();
    }
}
