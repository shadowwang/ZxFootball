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

    private class PlayerStatInfo {
        public String name;
        public String num;
        public String location;
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
