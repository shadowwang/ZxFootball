package com.parsonswang.zxfootball.matches;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.TeanImfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun on 2017/11/6.
 */

public class MatchInfoAdapter extends RecyclerView.Adapter {

    private List<MatchesBean.MatchInfo> matchInfoList = new ArrayList<>();

    public void addAll(List<MatchesBean.MatchInfo> matchInfoList) {
        if (matchInfoList != null && !matchInfoList.isEmpty()) {
            this.matchInfoList.addAll(matchInfoList);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MathInfoListItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_match_score, parent, false));
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
            mathInfoListItemVH.mTvMatchStatus.setText(matchInfo.isIsFinish() ? "已结束" : "未开始");
        }
    }

    @Override
    public int getItemCount() {
        return matchInfoList == null || matchInfoList.isEmpty() ? 0 : matchInfoList.size();
    }

    private class MathInfoListItemVH  extends RecyclerView.ViewHolder {

        TextView mTvScore, mTvMatchStatus;
        TeanImfoView mHomeTeam, mAwayTeam;

        public MathInfoListItemVH(View itemView) {
            super(itemView);
            mTvScore = itemView.findViewById(R.id.mTvScore);
            mTvMatchStatus = itemView.findViewById(R.id.mTvMatchStatus);
            mHomeTeam = itemView.findViewById(R.id.mLlHomeTeam);
            mAwayTeam = itemView.findViewById(R.id.mLlAwayTeam);
        }
    }
}
