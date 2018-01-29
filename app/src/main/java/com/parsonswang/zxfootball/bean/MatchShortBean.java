package com.parsonswang.zxfootball.bean;

/**
 * 射门数据
 * Created by parsonswang on 2018/1/26.
 */

public class MatchShortBean {

    /**
     * 总数
     */
    public String homeTotalShort;
    public String awayTotalShort;

    /**
     * 总进球数
     */
    public String homeTotalGoal;
    public String awayTotalGoal;

    /**
     * 总进球率
     */
    public String homeGoalRate;
    public String awayGoalRate;

    /**
     * 阵地战
     */
    public String homeOpenPlay;
    public String awayOpenPlay;

    /**
     * 定位球
     */
    public String homeSetPiece;
    public String awaySetPiece;

    /**
     * 反击
     */
    public String homeFastBrk;
    public String awayFastBrk;

    /**
     * 点球
     */
    public String homePenalty;
    public String awayPenalty;

    /**
     * 乌龙
     */
    public String homeOwnGoal;
    public String awayOwnGoal;

    @Override
    public String toString() {
        return "MatchShortBean{" +
                "homeTotalShort='" + homeTotalShort + '\'' +
                ", awayTotalShort='" + awayTotalShort + '\'' +
                ", homeTotalGoal='" + homeTotalGoal + '\'' +
                ", awayTotalGoal='" + awayTotalGoal + '\'' +
                ", homeGoalRate='" + homeGoalRate + '\'' +
                ", awayGoalRate='" + awayGoalRate + '\'' +
                ", homeOpenPlay='" + homeOpenPlay + '\'' +
                ", awayOpenPlay='" + awayOpenPlay + '\'' +
                ", homeSetPiece='" + homeSetPiece + '\'' +
                ", awaySetPiece='" + awaySetPiece + '\'' +
                ", homeFastBrk='" + homeFastBrk + '\'' +
                ", awayFastBrk='" + awayFastBrk + '\'' +
                ", homePenalty='" + homePenalty + '\'' +
                ", awayPenalty='" + awayPenalty + '\'' +
                ", homeOwnGoal='" + homeOwnGoal + '\'' +
                ", awayOwnGoal='" + awayOwnGoal + '\'' +
                '}';
    }
}
