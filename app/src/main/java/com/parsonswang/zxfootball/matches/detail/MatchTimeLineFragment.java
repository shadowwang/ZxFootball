package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

import timber.log.Timber;

/**
 * 比赛进程
 * Created by wangchun on 2018/2/6.
 */

public class MatchTimeLineFragment extends BaseLazyLoadFragment implements MatchContract.IMatchStatView{

    private static final String BUNDKE_KEY_MATCHSTAT = "match_stat";
    private SparseIntArray mTimeLineEventResMap = new SparseIntArray();

    private MatchPresenter matchPresenter;

    @Override
    protected void loadData() {
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL, R.drawable.ic_goal);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_ASSIST, R.drawable.ic_assist);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_GOAL_DIAN, R.drawable.ic_goal_dian);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_YELLOW_CARD, R.drawable.ic_yellow_card);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_2YELLOW_TO_RED, R.drawable.ic_2yellow_red);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_TO_RED, R.drawable.ic_red_card);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_SUBSTITUTES_DOWN, R.drawable.ic_player_down);
        mTimeLineEventResMap.put(Constant.MatchTimelineEventType.EVENTTYPE_SUBSTITUTES_UP, R.drawable.ic_player_up);

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_process, container, false);
        matchPresenter = new MatchPresenter(this);
        return view;
    }


    @Override
    public void getGoalPlayersInfo(GoalPlayers goalPlayers) {

    }

    @Override
    public void getMatchTimelineInfo(MatchStatBean matchStatBean) {
        Timber.i(matchStatBean.matchTimelinesList.toString());
    }

    @Override
    public void showExceptionView() {

    }
}
