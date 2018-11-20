package com.parsonswang.zxfootball.bean;

public class MatchPlayerStatInfo {
    public TeamInfo homeTeamInfo;
    public TeamInfo awayTeamInfo;

    private class TeamInfo {
        public String teamLogo;
        public String team;
    }
}
