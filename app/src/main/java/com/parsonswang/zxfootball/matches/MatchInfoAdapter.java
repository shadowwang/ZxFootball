package com.parsonswang.zxfootball.matches;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsonswang.common.view.pinheader.AdapterStick;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.TeamInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun on 2017/11/6.
 */

public class MatchInfoAdapter extends RecyclerView.Adapter implements AdapterStick {

    private List<MatchesBean.MatchInfo> matchInfoList = new ArrayList<>();

    public void addAll(List<MatchesBean.MatchInfo> matchInfoList) {
        if (matchInfoList != null && !matchInfoList.isEmpty()) {
            this.matchInfoList.addAll(matchInfoList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.matchInfoList.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MatchesBean.MatchInfo.TYPE_TITLE) {
            return new MatchInfoDateItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_match_score_pinned_header, parent, false));
        } else if (viewType == MatchesBean.MatchInfo.TYPE_NORMAL) {
            return new MathInfoListItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_match_score, parent, false));
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (matchInfoList == null || matchInfoList.isEmpty()) {
            return;
        }

        final MatchesBean.MatchInfo matchInfo = matchInfoList.get(position);
        if (holder instanceof MathInfoListItemVH) {
            MathInfoListItemVH mathInfoListItemVH = (MathInfoListItemVH) holder;
            mathInfoListItemVH.mTvScore.setText(matchInfo.getScore());
            if (matchInfo.isIsFinish()) {
                mathInfoListItemVH.mTvScore.setVisibility(View.VISIBLE);
                mathInfoListItemVH.mTvMatchStatus.setText("已结束");
            } else {
                mathInfoListItemVH.mTvScore.setVisibility(View.GONE);
                mathInfoListItemVH.mTvMatchStatus.setText("未开始");
            }
            mathInfoListItemVH.mTvStage.setText(matchInfo.getStageName());
            mathInfoListItemVH.mHomeTeam.setInfo(matchInfo.getHomeTeamId(), matchInfo.getHomeTeamName());
            mathInfoListItemVH.mAwayTeam.setInfo(matchInfo.getAwayTeamId(), matchInfo.getAwayTeamName());
        } else if (holder instanceof MatchInfoDateItemVH) {
            MatchInfoDateItemVH matchInfoDateItemVH = (MatchInfoDateItemVH) holder;
            matchInfoDateItemVH.mTvMatchDate.setText(matchInfo.getMatchDate());
        }
    }

    @Override
    public int getItemCount() {
        return matchInfoList == null || matchInfoList.isEmpty() ? 0 : matchInfoList.size();
    }

    @Override
    public int getHeaderCount() {
        return 0;
    }

    @Override
    public boolean isPinnedViewType(int viewType) {
        return viewType == MatchesBean.MatchInfo.TYPE_TITLE;
    }

    private class MathInfoListItemVH  extends RecyclerView.ViewHolder {

        TextView mTvScore, mTvMatchStatus, mTvStage;
        TeamInfoView mHomeTeam, mAwayTeam;

        public MathInfoListItemVH(View itemView) {
            super(itemView);
            mTvScore = itemView.findViewById(R.id.mTvScore);
            mTvMatchStatus = itemView.findViewById(R.id.mTvMatchStatus);
            mHomeTeam = itemView.findViewById(R.id.mLlHomeTeam);
            mAwayTeam = itemView.findViewById(R.id.mLlAwayTeam);
            mTvStage = itemView.findViewById(R.id.mTvStage);
        }
    }

    private class MatchInfoDateItemVH extends RecyclerView.ViewHolder {

        TextView mTvMatchDate;

        public MatchInfoDateItemVH(View itemView) {
            super(itemView);
            mTvMatchDate = itemView.findViewById(R.id.mTvMatchDate);
        }
    }
}
