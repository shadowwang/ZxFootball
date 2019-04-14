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
        public static final int TYPE_TITLE = 0x01;
        public static final int TYPE_NORMAL = 0x02;

        public int type = TYPE_NORMAL;
        public String name;
        public String desc;
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

        @Override
        public String toString() {
            return "PlayerStatInfo{" +
                    "name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", playerPageUrl='" + playerPageUrl + '\'' +
                    ", shootCnt=" + shootCnt +
                    ", shootOnTargetCnt=" + shootOnTargetCnt +
                    ", keyPass=" + keyPass +
                    ", psPercent=" + psPercent +
                    ", makeChanceCnt=" + makeChanceCnt +
                    ", headingDuelCnt=" + headingDuelCnt +
                    ", touchBallCnt=" + touchBallCnt +
                    ", scoreGrade=" + scoreGrade +
                    '}';
        }
    }



}
