package com.parsonswang.zxfootball.matches.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatListAdapter extends RecyclerView.Adapter {

    private List<MatchPlayerStatInfo.PlayerStatInfo> playerStatInfos = new ArrayList<>();

    public void addAll(List<MatchPlayerStatInfo.PlayerStatInfo> playerStatInfos) {
        if (playerStatInfos != null && !playerStatInfos.isEmpty()) {
            this.playerStatInfos.addAll(playerStatInfos);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MatchPlayerStatInfo.PlayerStatInfo.TYPE_TITLE) {
            return new PlayerStatHeaderVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_player_stat_header, parent, false));
        }
        return new PlayerStatItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_player_stat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return playerStatInfos == null || playerStatInfos.isEmpty() ? 0 : playerStatInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return playerStatInfos.get(position).type;
    }

    private class PlayerStatHeaderVH extends RecyclerView.ViewHolder {

        public PlayerStatHeaderVH(View itemView) {
            super(itemView);
        }
    }

    private class PlayerStatItemVH extends RecyclerView.ViewHolder {

        public PlayerStatItemVH(View itemView) {
            super(itemView);
        }
    }

}
