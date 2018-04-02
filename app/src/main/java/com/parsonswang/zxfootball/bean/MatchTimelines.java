package com.parsonswang.zxfootball.bean;

/**
 * 比赛进程
 */
public class MatchTimelines {

    public String playerName;
    public int second;
    public int eventType;
    public String teamId;
    public String id;
    public int minute;
    public int eventId;
    public String playerId;
    public String description;
    public String matchId;

    @Override
    public String toString() {
        return "MatchTimelines{" +
                "playerName='" + playerName + '\'' +
                ", second=" + second +
                ", eventType=" + eventType +
                ", teamId='" + teamId + '\'' +
                ", id='" + id + '\'' +
                ", minute=" + minute +
                ", eventId=" + eventId +
                ", playerId='" + playerId + '\'' +
                ", description='" + description + '\'' +
                ", matchId='" + matchId + '\'' +
                '}';
    }
}
