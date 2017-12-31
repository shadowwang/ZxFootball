package com.parsonswang.zxfootball.bean;

/**
 * 比赛头部信息
 * Created by wangchun on 2017/12/28.
 */

public class MatchDetailHeaderInfoBean {

    public String homeTeamName;

    public String homeTeamUrl;

    public String homeTeamLogo;

    public String awayTeamName;

    public String awayTeamUrl;

    public String awayTeamLogo;

    public String halfTimeScore;

    public String endTimeScore;

    public String matchStatus;

    public String matchTime;

    public String matchLun;

    @Override
    public String toString() {
        return "MatchDetailHeaderInfoBean{" +
                "homeTeamName='" + homeTeamName + '\'' +
                ", homeTeamUrl='" + homeTeamUrl + '\'' +
                ", homeTeamLogo='" + homeTeamLogo + '\'' +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", awayTeamUrl='" + awayTeamUrl + '\'' +
                ", awayTeamLogo='" + awayTeamLogo + '\'' +
                ", halfTimeScore='" + halfTimeScore + '\'' +
                ", endTimeScore='" + endTimeScore + '\'' +
                ", matchStatus='" + matchStatus + '\'' +
                ", matchTime='" + matchTime + '\'' +
                '}';
    }
}
