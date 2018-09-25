package com.parsonswang.zxfootball.bean;

/**
 * 球员信息
 */
public class PlayerInfo {
    public String playerId;
    public String shirtNum;
    public String avatarUrl;
    public String playerName;
    public String teamId;
    public boolean hasExchangeDown;//是否被换下
    public int downMinute;//被换下时间

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "playerId=" + playerId +
                ", shirtNum='" + shirtNum + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", playerName='" + playerName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
