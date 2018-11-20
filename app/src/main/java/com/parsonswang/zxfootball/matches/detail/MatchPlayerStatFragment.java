package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

/**
 * 球员统计Fragment
 * Created by wangchun on 2018/2/6.
 */

public class MatchPlayerStatFragment extends BaseLazyLoadFragment implements MatchContract.IMatchPlayerInfoView {

    private static final String BUNDKE_KEY_MATCHID = "match_id";

    public static MatchPlayerStatFragment newInstance(String matchId) {
        MatchPlayerStatFragment matchPlayerInfoFragment = new MatchPlayerStatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDKE_KEY_MATCHID, matchId);
        matchPlayerInfoFragment.setArguments(bundle);
        return matchPlayerInfoFragment;
    }

    private MatchPresenter matchPresenter;

    @Override
    protected void loadData() {
        if (matchPresenter != null) {
            matchPresenter.getMatchPlayerInfos(getArguments().getString(BUNDKE_KEY_MATCHID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_player_stat, container, false);
        matchPresenter = new MatchPresenter(this);
        return view;
    }

    @Override
    public void getMatchPlayerInfo() {

    }

    @Override
    public void showExceptionView() {

    }
}
