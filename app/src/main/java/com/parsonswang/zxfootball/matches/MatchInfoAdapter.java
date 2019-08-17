package com.parsonswang.zxfootball.matches;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parsonswang.common.view.pinheader.AdapterStick;
import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.view.MatchScoreInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun on 2017/11/6.
 */

public class MatchInfoAdapter extends RecyclerView.Adapter implements AdapterStick {


    private List<MatchesBean.MatchInfo> matchInfoList = new ArrayList<>();

    private Context mContext;

    public MatchInfoAdapter(Context context) {
        this.mContext = context;
    }

    public void addAll(List<MatchesBean.MatchInfo> matchInfoList) {
        if (matchInfoList != null && !matchInfoList.isEmpty()) {
            this.matchInfoList.addAll(matchInfoList);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        if (matchInfoList != null && !matchInfoList.isEmpty()) {
            matchInfoList.clear();
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
            return new MathInfoListItemVH(new MatchScoreInfoView<MatchesBean.MatchInfo>(mContext));
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
            mathInfoListItemVH.matchScoreInfoView.setData(matchInfo);
            mathInfoListItemVH.matchScoreInfoView.mTvScore.setText(matchInfo.getScore());
            if (matchInfo.isIsFinish()) {
                mathInfoListItemVH.matchScoreInfoView.mTvScore.setVisibility(View.VISIBLE);
                mathInfoListItemVH.matchScoreInfoView.mTvMatchStatus.setText("已结束");
            } else {
                mathInfoListItemVH.matchScoreInfoView.mTvScore.setVisibility(View.GONE);
                mathInfoListItemVH.matchScoreInfoView.mTvMatchStatus.setText("未开始");
            }
            mathInfoListItemVH.matchScoreInfoView.mTvStage.setText(matchInfo.getStageName());
            mathInfoListItemVH.matchScoreInfoView.mHomeTeam.setInfo(matchInfo, true);
            mathInfoListItemVH.matchScoreInfoView.mAwayTeam.setInfo(matchInfo, false);

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

    private class MathInfoListItemVH extends RecyclerView.ViewHolder {

        MatchScoreInfoView<MatchesBean.MatchInfo> matchScoreInfoView;

        public MathInfoListItemVH(View itemView) {
            super(itemView);
            matchScoreInfoView = (MatchScoreInfoView<MatchesBean.MatchInfo>) itemView;
            matchScoreInfoView.setCorner();
            matchScoreInfoView.setMarginLeftAndRight(14.4F, 14.4F);
            matchScoreInfoView.setOnItemClickListener(mOnItemClickListener);
        }
    }

    private class MatchInfoDateItemVH extends RecyclerView.ViewHolder {

        TextView mTvMatchDate;

        public MatchInfoDateItemVH(View itemView) {
            super(itemView);
            mTvMatchDate = itemView.findViewById(R.id.mTvMatchDate);
        }
    }

    private MatchScoreInfoView.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(MatchScoreInfoView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
