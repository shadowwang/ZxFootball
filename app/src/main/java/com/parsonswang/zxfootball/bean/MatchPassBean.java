package com.parsonswang.zxfootball.bean;

/**
 * 传球数据bean
 * Created by parsonswang on 2018/1/29.
 */

public class MatchPassBean {
    public String homePassTotal;
    public String awayPassTotal;

    public String homeShortPass;
    public String awayShortPass;

    public String homeLongPass;
    public String awayLongPass;

    public String homeCrossPass;
    public String awayCrossPass;

    public String homeThroughPass;
    public String awayThroughPass;

    public String homePerPass;
    public String awayPerPass;

    public String homeTotalPer;
    public String awayTotalPer;

    @Override
    public String toString() {
        return "MatchPassBean{" +
                "homePassTotal='" + homePassTotal + '\'' +
                ", awayPassTotal='" + awayPassTotal + '\'' +
                ", homeShortPass='" + homeShortPass + '\'' +
                ", awayShortPass='" + awayShortPass + '\'' +
                ", homeLongPass='" + homeLongPass + '\'' +
                ", awayLongPass='" + awayLongPass + '\'' +
                ", homeCrossPass='" + homeCrossPass + '\'' +
                ", awayCrossPass='" + awayCrossPass + '\'' +
                ", homeThroughPass='" + homeThroughPass + '\'' +
                ", awayThroughPass='" + awayThroughPass + '\'' +
                ", homePerPass='" + homePerPass + '\'' +
                ", awayPerPass='" + awayPerPass + '\'' +
                ", homeTotalPer='" + homeTotalPer + '\'' +
                ", awayTotalPer='" + awayTotalPer + '\'' +
                '}';
    }
}
