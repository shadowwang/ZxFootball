package com.parsonswang.zxfootball.matches.detail;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.common.view.CommonRecyclerViewDivider;
import com.parsonswang.zxfootball.matches.MatchContract;
import com.parsonswang.zxfootball.matches.MatchPresenter;

import java.util.ArrayList;

/**
 * 球员统计Fragment
 * Created by wangchun on 2018/2/6.
 */

public class MatchPlayerStatFragment extends BaseLazyLoadFragment implements MatchContract.IMatchPlayerInfoView, MatchContract.IMatchDetailView {

    private static final String BUNDKE_KEY_MATCHID = "match_id";


    private RecyclerView mRvHomePlayerStatList, mRvAwayPlayerStatList;
    private PlayerStatListAdapter mHomeStatListAdapter, mAwayStatListAdapter;
    private ImageView mHomeTeamLogo, mAwayTeamLogo;
    private TextView mHomeTeamName, mAwayTeamName;

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

    private void initPlayerStatList(RecyclerView recyclerView, PlayerStatListAdapter playerStatListAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(playerStatListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new CommonRecyclerViewDivider( getContext(),
                LinearLayoutManager.VERTICAL,
                UIUtils.dip2px(mContext, 5F),
                Color.parseColor("#232C30")));
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_player_stat, container, false);

        mRvHomePlayerStatList = (RecyclerView) view.findViewById(R.id.mRvHomePlayerStatList);
        mRvAwayPlayerStatList = (RecyclerView) view.findViewById(R.id.mRvAwayPlayerStatList);

        mHomeStatListAdapter = new PlayerStatListAdapter(mContext);
        mAwayStatListAdapter = new PlayerStatListAdapter(mContext);

        initPlayerStatList(mRvHomePlayerStatList, mHomeStatListAdapter);
        initPlayerStatList(mRvAwayPlayerStatList, mAwayStatListAdapter);

        mHomeTeamLogo = (ImageView) view.findViewById(R.id.mHomeTeamLogo);
        mAwayTeamLogo = (ImageView) view.findViewById(R.id.mAwayTeamLogo);

        mHomeTeamName = (TextView) view.findViewById(R.id.mHomeTeamName);
        mAwayTeamName = (TextView) view.findViewById(R.id.mAwayTeamName);

        matchPresenter = new MatchPresenter(this, this);
        matchPresenter.getMatchDetail(getArguments().getString(BUNDKE_KEY_MATCHID));
        return view;
    }

    @Override
    public void getMatchPlayerInfo(ArrayList<MatchPlayerStatInfo.PlayerStatInfo> homePlayerStatInfos,
                                   ArrayList<MatchPlayerStatInfo.PlayerStatInfo> awayPlayerStatInfos) {
        MatchPlayerStatInfo.PlayerStatInfo title = new MatchPlayerStatInfo.PlayerStatInfo();
        title.type = MatchPlayerStatInfo.PlayerStatInfo.TYPE_TITLE;

        homePlayerStatInfos.add(0, title);
        mHomeStatListAdapter.addAll(homePlayerStatInfos);

        awayPlayerStatInfos.add(0, title);
        mAwayStatListAdapter.addAll(awayPlayerStatInfos);
    }

    @Override
    public void showExceptionView() {

    }

    @Override
    public void showMatchInfoHeader(MatchDetailHeaderInfoBean matchDetailHeaderInfoBean) {
        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.homeTeamLogo, mHomeTeamLogo, 0);
        mHomeTeamName.setText(matchDetailHeaderInfoBean.homeTeamName);

        Imageloaders.loadImage(mContext, matchDetailHeaderInfoBean.awayTeamLogo, mAwayTeamLogo, 0);
        mAwayTeamName.setText(matchDetailHeaderInfoBean.awayTeamName);
    }

    @Override
    public void showMatchSummary(MatchSummary matchSummary) {}
}
