package com.parsonswang.zxfootball.bean;

import java.util.List;

public class MatchStatBean {

    public List<MatchTimelines> matchTimelinesList;

    public String homeTeamId;

    public String awayTeamId;

    @Override
    public String toString() {
        return "MatchStatBean{" +
                "matchTimelinesList=" + matchTimelinesList +
                ", homeTeamId='" + homeTeamId + '\'' +
                ", awayTeamId='" + awayTeamId + '\'' +
                '}';
    }
}
