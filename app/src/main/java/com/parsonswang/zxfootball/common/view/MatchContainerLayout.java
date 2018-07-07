package com.parsonswang.zxfootball.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.parsonswang.common.utils.StringUtils;
import com.parsonswang.common.utils.UIUtils;
import com.parsonswang.zxfootball.bean.MatchTimelines;
import com.parsonswang.zxfootball.bean.PlayerInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * 比赛球员信息container
 */
public class MatchContainerLayout extends TableLayout {

    public MatchContainerLayout(@NonNull Context context) {
        super(context);
    }

    public MatchContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setGravity(VERTICAL);
    }

    public void addTimeLine(LinkedList<PlayerInfo> homeMainPlayerInfos,
                            LinkedList<PlayerInfo> awayMainPlayerInfos,
                            List<MatchTimelines> matchTimelines) {

    }

    public void addPlayer(String homeTeamFormation,
                          LinkedList<PlayerInfo> homeMainPlayerInfos,
                          String awayTeamFormation,
                          LinkedList<PlayerInfo> awayMainPlayerInfos,
                          int height) {
        //=====主队球员（首发）=====
        final ArrayList<String> homeRowInfo = getRowCnt(homeTeamFormation);
        int homeRowCnt = homeRowInfo.size() + 1;

        final int homeRowHeight = height / 2 / homeRowCnt;
        Timber.i("homeRowHeight: " + homeRowHeight);

        for (int i = 0; i < homeRowCnt; i++) {
            TableRow tableRow = new TableRow(getContext());
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = homeRowHeight;
            tableRow.setOrientation(HORIZONTAL);
            addView(tableRow, layoutParams);

            if (i == 0) {//主队门将位置
                MatchPlayerView playerView = new MatchPlayerView(getContext());
                final PlayerInfo playerInfo = homeMainPlayerInfos.poll();
                if (playerInfo != null) {
                    playerView.setData(playerInfo.avatarUrl, playerInfo.playerName);
                }

                tableRow.addView(playerView);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                playerView.getLayoutParams().height = homeRowHeight;
//                ((LinearLayout.LayoutParams)playerView.getLayoutParams()).topMargin = UIUtils.dip2px(getContext(), 5);
            } else {
//                ((LinearLayout.LayoutParams)tableRow.getLayoutParams()).topMargin = UIUtils.dip2px(getContext(), 15);

                int homeCntPerRow =  Integer.parseInt(homeRowInfo.get(i - 1));
                Timber.i("homeCntPerRow: " + homeCntPerRow);

                for (int j = 0; j < homeCntPerRow; j++) {
                    MatchPlayerView playerView = new MatchPlayerView(getContext());
                    final PlayerInfo playerInfo = homeMainPlayerInfos.poll();
                    if (playerInfo != null) {
                        playerView.setData(playerInfo.avatarUrl, playerInfo.playerName);
                    }
                    tableRow.setWeightSum(homeCntPerRow);
                    tableRow.addView(playerView);
                    tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                    ((LinearLayout.LayoutParams)playerView.getLayoutParams()).weight = 1;
                    playerView.getLayoutParams().height = homeRowHeight;
                }
            }
        }

        //=========客队球员(首发)=============
        final ArrayList<String> awayRowInfo = getRowCnt(awayTeamFormation);
        int awayRowCnt = awayRowInfo.size() + 1;
        Timber.i("awayRowCnt: " + awayRowCnt);

        final int awayRowHeight = height / 2 / awayRowCnt;
        for (int i = 0; i < awayRowCnt; i++) {
            TableRow tableRow = new TableRow(getContext());
            addView(tableRow);
            tableRow.getLayoutParams().height = awayRowHeight;

            if (i == awayRowCnt - 1) {
                MatchPlayerView goalKeeper = new MatchPlayerView(getContext());
                final PlayerInfo playerInfo = awayMainPlayerInfos.poll();
                if (playerInfo != null) {
                    goalKeeper.setData(playerInfo.avatarUrl, playerInfo.playerName);
                }
                tableRow.addView(goalKeeper);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);

            } else {
                int awayCntPerRow =  Integer.parseInt(awayRowInfo.get(awayRowCnt - i - 2));
                Timber.i("awayCntPerRow: " + awayCntPerRow);
                for (int j = 0; j < awayCntPerRow; j++) {
                    MatchPlayerView playerView = new MatchPlayerView(getContext());
                    final PlayerInfo playerInfo = awayMainPlayerInfos.pollLast();
                    if (playerInfo != null) {
                        playerView.setData(playerInfo.avatarUrl, playerInfo.playerName);
                    }
                    tableRow.setWeightSum(awayCntPerRow);
                    tableRow.addView(playerView);
                    tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                    ((LinearLayout.LayoutParams)playerView.getLayoutParams()).weight = 1;
                    playerView.getLayoutParams().height = homeRowHeight;
                    if (j == 0) {
                        ((LinearLayout.LayoutParams)playerView.getLayoutParams()).topMargin = UIUtils.dip2px(getContext(), 5);
                    }
                }
            }
        }
    }

    /**
     * 根据阵型得到行数
     * @param formation
     * @return
     */
    private ArrayList<String> getRowCnt(String formation) {
        ArrayList<String> res = new ArrayList<>();

        if (StringUtils.isEmptyString(formation)) {
            return res;
        }

        String[] arr = formation.split("-");
        if (arr.length == 0) {
            return res;
        }

        return new ArrayList<>(Arrays.asList(arr));
    }

}
