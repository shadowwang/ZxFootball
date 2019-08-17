package com.parsonswang.zxfootball.matches.detail;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;
import com.parsonswang.zxfootball.common.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MatchPlayerStatInfo.PlayerStatInfo> playerStatInfos = new ArrayList<>();

    public PlayerStatListAdapter(Context context) {
        this.mContext = context;
    }

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
        if (playerStatInfos == null || playerStatInfos.isEmpty()) {
            return;
        }

        final MatchPlayerStatInfo.PlayerStatInfo playerStatInfo = playerStatInfos.get(position);

        if (holder instanceof PlayerStatItemVH) {
            PlayerStatItemVH playerStatItemVH = (PlayerStatItemVH) holder;
            playerStatItemVH.mTvDesc.setText(playerStatInfo.desc);
            playerStatItemVH.mTvHeadingDuelCnt.setText(playerStatInfo.headingDuelCnt + "");
            playerStatItemVH.mTvKeyPass.setText(playerStatInfo.keyPass + "");
            playerStatItemVH.mTvMakeChanceCnt.setText(playerStatInfo.makeChanceCnt + "");
            playerStatItemVH.mTvPlayerName.setText(playerStatInfo.name);
            playerStatItemVH.mTvPsPercent.setText(String.valueOf(playerStatInfo.psPercent));
            playerStatItemVH.mTvScoreGrade.setText(String.valueOf(playerStatInfo.scoreGrade));
            playerStatItemVH.mTvShootCnt.setText(playerStatInfo.shootCnt + "");
            playerStatItemVH.mTvShootOnTargetCnt.setText(playerStatInfo.shootOnTargetCnt + "");
            playerStatItemVH.mTvTouchBallCnt.setText(playerStatInfo.touchBallCnt + "");

            ImageUtils.loadMatchPlayerAvatar(mContext, playerStatItemVH.mIvPlayerAvatar, playerStatInfo.avatar);
        }
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

        ImageView mIvPlayerAvatar;
        TextView mTvPlayerName;
        TextView mTvDesc;
        TextView mTvShootCnt;
        TextView mTvShootOnTargetCnt;
        TextView mTvKeyPass;
        TextView mTvPsPercent;
        TextView mTvMakeChanceCnt;
        TextView mTvHeadingDuelCnt;
        TextView mTvTouchBallCnt;
        TextView mTvScoreGrade;

        public PlayerStatItemVH(View itemView) {
            super(itemView);
            mIvPlayerAvatar = itemView.findViewById(R.id.mIvPlayerAvatar);
            mTvPlayerName = itemView.findViewById(R.id.mTvPlayerName);
            mTvDesc = itemView.findViewById(R.id.mTvDesc);
            mTvShootCnt = itemView.findViewById(R.id.mTvShootCnt);
            mTvShootOnTargetCnt = itemView.findViewById(R.id.mTvShootOnTargetCnt);
            mTvKeyPass = itemView.findViewById(R.id.mTvKeyPass);
            mTvPsPercent = itemView.findViewById(R.id.mTvPsPercent);
            mTvMakeChanceCnt = itemView.findViewById(R.id.mTvMakeChanceCnt);
            mTvHeadingDuelCnt = itemView.findViewById(R.id.mTvHeadingDuelCnt);
            mTvTouchBallCnt = itemView.findViewById(R.id.mTvTouchBallCnt);
            mTvScoreGrade = itemView.findViewById(R.id.mTvScoreGrade);

        }
    }

}
