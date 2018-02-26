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

    private String listToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }

        final StringBuilder stringBuffer = new StringBuilder();
        for (String str : list) {
            stringBuffer.append(str).append("\r\n");
        }

        return stringBuffer.toString();
    }

    public String getHomeMatchSummary() {
        String result = "强点:\r\n";

        result += listToString(homeStronger);

        result += "弱点:\r\n";

        result += listToString(homeWeaker);

        result += "比赛风格:\r\n";

        result += listToString(homeMatchStyle);

        return result;
    }

    public String getAwayMatchSummary() {
        String result = "强点:\r\n";
        result += listToString(awayStronger);

        result += "弱点:\r\n";
        result += listToString(awayWeaker);

        result += "比赛风格:\r\n";
        result += listToString(awayMatchStyle);
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
