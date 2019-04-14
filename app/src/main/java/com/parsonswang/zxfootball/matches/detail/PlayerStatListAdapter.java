package com.parsonswang.zxfootball.matches.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;

import java.util.List;

public class PlayerStatListAdapter extends RecyclerView.Adapter {

    private List<MatchPlayerStatInfo.PlayerStatInfo> playerStatInfos;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return playerStatInfos.get(position).type;
    }
}
