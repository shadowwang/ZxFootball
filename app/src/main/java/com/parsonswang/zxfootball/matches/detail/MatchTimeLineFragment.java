package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.common.view.FootballView;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.bean.MatchTimelines;
import com.parsonswang.zxfootball.bean.PlayerInfo;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.common.view.MatchContainerLayout;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * 比赛进程
 * Created by wangchun on 2018/2/6.
 */

public class MatchTimeLineFragment extends BaseLazyLoadFragment implements MatchContract.IMatchStatView{

    private static final String BUNDKE_KEY_MATCHSTAT = "match_stat";
    private SparseIntArray mTimeLineEventResMap = new SparseIntArray();

    private MatchPresenter matchPresenter;

    private MatchContainerLayout mMatchContainerLayout;
    private FootballView mFootballView;

    @Override
    protected void loadData() {
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL, R.drawable.ic_goal);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_ZHONGZHU, R.drawable.ic_zhongzhu);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_ASSIST, R.drawable.ic_assist);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN, R.drawable.ic_goal_dian);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_YELLOW_CARD, R.drawable.ic_yellow_card);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_2YELLOW_TO_RED, R.drawable.ic_2yellow_red);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_TO_RED, R.drawable.ic_red_card);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_SUBSTITUTES_DOWN, R.drawable.ic_player_down);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_SUBSTITUTES_UP, R.drawable.ic_player_up);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_STEAL, R.drawable.ic_steal);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_SAVE, R.drawable.ic_save);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN_NOT, R.drawable.ic_dian_not_goal);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN_SAVE, R.drawable.ic_dian_save);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_WULONG_GOAL, R.drawable.ic_goal_own);

        if (matchPresenter != null) {
            matchPresenter.getMatchStat(getArguments().getString(BUNDKE_KEY_MATCHSTAT));
        }
    }

    public static MatchTimeLineFragment newInstance(String matchId) {
        MatchTimeLineFragment matchTimeLineFragment = new MatchTimeLineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDKE_KEY_MATCHSTAT, matchId);
        matchTimeLineFragment.setArguments(bundle);
        return matchTimeLineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_process, container, false);
        mMatchContainerLayout = view.findViewById(R.id.match_container);
        mFootballView = view.findViewById(R.id.football_view);


        matchPresenter = new MatchPresenter(this);

        return view;
    }


    @Override
    public void getGoalPlayersInfo(GoalPlayers goalPlayers) {}

    @Override
    public void getMatchTimelineInfo(MatchStatBean matchStatBean) {
        //添加timeline
        final List<MatchTimelines> matchTimelinesList = matchStatBean.matchTimelinesList;

        HashMap<String, ArrayList<MatchTimelines>> timelinesHashMap = new HashMap<>(matchTimelinesList.size());

        HashSet<String> keySet = new HashSet<>();

        for (MatchTimelines timelines : matchTimelinesList) {
//            timelinesHashMap.put(timelines.playerId, timelines);
            ArrayList<MatchTimelines> timelinesArrayList = null;
            if (!keySet.contains(timelines.playerId)) {
                keySet.add(timelines.playerId);
                timelinesArrayList = new ArrayList<>();
                timelinesArrayList.add(timelines);
                timelinesHashMap.put(timelines.playerId, timelinesArrayList);
            } else {
                if (timelinesHashMap.get(timelines.playerId) != null) {
                    timelinesHashMap.get(timelines.playerId).add(timelines);
                }
            }
        }

        Timber.i(matchStatBean.homeBenchPlayerInfos.toString());
        Timber.i(matchStatBean.awayBenchPlayerInfos.toString());


        //添加出场球员和timeline信息
        mMatchContainerLayout.addPlayer(matchStatBean.homeTeamFormation,
                matchStatBean.homeMainPlayerInfos,
                matchStatBean.homeBenchPlayerInfos,
                matchStatBean.awayTeamFormation,
                matchStatBean.awayMainPlayerInfos,
                matchStatBean.awayBenchPlayerInfos,
                mFootballView.getMeasuredHeight(),
                timelinesHashMap, mTimeLineEventResMap);


    }

    @Override
    public void showExceptionView() {}
}
