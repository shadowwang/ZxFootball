package com.parsonswang.zxfootball.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 比赛总结
 * Created by wangchun on 2018/1/2.
 */

public class MatchSummary {

    public List<String> homeStronger = new ArrayList<>();
    public List<String> awayStronger = new ArrayList<>();

    public List<String> homeWeaker = new ArrayList<>();
    public List<String> awayWeaker = new ArrayList<>();

    public List<String> homeMatchStyle = new ArrayList<>();
    public List<String> awayMatchStyle = new ArrayList<>();

    public String getHomeMatchSummary() {
        String result = "强点:\r\n";

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : homeStronger) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();

        result += "弱点:\r\n";
        for (String str : homeWeaker) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();

        result += "比赛风格:\r\n";
        for (String str : homeMatchStyle) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();
        return result;
    }

    public String getAwayMatchSummary() {
        String result = "强点:\r\n";

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : awayStronger) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();

        result += "弱点:\r\n";
        for (String str : awayWeaker) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();

        result += "比赛风格:\r\n";
        for (String str : awayMatchStyle) {
            stringBuilder.append(str).append("\r\n");
        }
        result += stringBuilder.toString();
        return result;
    }

    @Override
    public String toString() {
        return "MatchSummary{" +
                "homeStronger=" + homeStronger +
                ", awayStronger=" + awayStronger +
                ", homeWeaker=" + homeWeaker +
                ", awayWeaker=" + awayWeaker +
                ", homeMatchStyle=" + homeMatchStyle +
                ", awayMatchStyle=" + awayMatchStyle +
                '}';
    }
}
