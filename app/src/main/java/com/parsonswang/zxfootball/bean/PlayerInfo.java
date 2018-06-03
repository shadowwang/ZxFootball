package com.parsonswang.zxfootball.bean;

/**
 * 球员信息
 */
public class PlayerInfo {
    public String playerId;
    public String shirtNum;
    public String avatarUrl;
    public String playerName;

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "playerId=" + playerId +
                ", shirtNum='" + shirtNum + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}
