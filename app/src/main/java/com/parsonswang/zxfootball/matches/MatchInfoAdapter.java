package com.parsonswang.zxfootball.matches;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.bean.MatchesBean;

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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
