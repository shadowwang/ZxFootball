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
