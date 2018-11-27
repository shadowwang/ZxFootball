package com.parsonswang.zxfootball.bean;

import java.util.ArrayList;

public class MatchPlayerStatInfo {
    public TeamInfo homeTeamInfo;
    public TeamInfo awayTeamInfo;

    public ArrayList<PlayerStatInfo> homePlayerInfo;
    public ArrayList<PlayerStatInfo> awayPlayerInfo;

    private class TeamInfo {
        public String teamLogo;
        public String team;
    }

    public static class PlayerStatInfo {
        public String name;
        public String num;
        public int age;
        public String location;
        public String avatar;
        public String playerPageUrl;
        public int shootCnt;
        public int shootOnTargetCnt;
        public int keyPass;
        public float psPercent;
        public int makeChanceCnt;
        public int headingDuelCnt;
        public int touchBallCnt;
        public float scoreGrade;
    }

}
