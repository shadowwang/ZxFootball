package com.parsonswang.zxfootball.bean;

/**
 * 进球队员
 * Created by wangchun on 2018/3/30.
 */

public class GoalPlayers {
    public String homeGoalPlayers;
    public String awayGoalPlayers;

    @Override
    public String toString() {
        return "GoalPlayers{" +
                "homeGoalPlayers='" + homeGoalPlayers + '\'' +
                ", awayGoalPlayers='" + awayGoalPlayers + '\'' +
                '}';
    }
}
