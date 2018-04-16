package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.common.Constant;

/**
 * 比赛进程
 * Created by wangchun on 2018/2/6.
 */

public class MatchTimeLineFragment extends BaseLazyLoadFragment {

    private SparseIntArray mTimeLineEventResMap = new SparseIntArray();

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
    }

    public static MatchTimeLineFragment newInstance() {
        MatchTimeLineFragment matchTimeLineFragment = new MatchTimeLineFragment();
        return matchTimeLineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_process, container, false);
        return view;
    }


}
