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
import com.parsonswang.zxfootball.bean.PlayerInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 比赛球员信息container
 */
public class MatchContainerLayout extends TableLayout {

    public MatchContainerLayout(@NonNull Context context) {
        super(context);
    }

    public MatchContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addPlayer(String homeTeamFormation,
                          LinkedList<PlayerInfo> homeMainPlayerInfos,
                          String awayTeamFormation,
                          LinkedList<PlayerInfo> awayMainPlayerInfos,
                          int height) {
        //=====主队球员（场上）=====
        final ArrayList<String> homeRowInfo = getRowCnt(homeTeamFormation);
        int homeRowCnt = homeRowInfo.size();
        for (int i = 0; i < homeRowCnt; i++) {
            TableRow tableRow = new TableRow(getContext());
            addView(tableRow);
            tableRow.getLayoutParams().height = height / 2 / homeRowCnt;

            if (i == 0) {//主队门将位置
                MatchPlayerView goalKeeper = new MatchPlayerView(getContext());
                final PlayerInfo playerInfo = homeMainPlayerInfos.poll();
                if (playerInfo != null) {
                    goalKeeper.setData(playerInfo.avatarUrl, playerInfo.playerName);
                }
                tableRow.addView(goalKeeper);
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                ((LinearLayout.LayoutParams)goalKeeper.getLayoutParams()).topMargin = UIUtils.dip2px(getContext(), 5);
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
