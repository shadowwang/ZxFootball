package com.parsonswang.zxfootball.common;

import com.parsonswang.zxfootball.common.utils.ConfigUtil;

/**
 * Created by wangchun on 2017/10/22.
 */

public class Constant {

    public static class NetworkProtocolConstant {

        public static final String HTTP_REQUEST_URL = ConfigUtil.getOnlineConfig("server_host", "http://www.tzuqiu.cc/");

        public static final String MATCH_QUERY_URL = HTTP_REQUEST_URL + "matches/queryFixture.json";

        public static final String MATCH_DETAIL_URL = HTTP_REQUEST_URL + "matches/%1$s/report.do";

        public static final String MATCH_STAT_URL = HTTP_REQUEST_URL + "matches/%1$s/stat.do";

    }


    public static class TeamImageSize {
        public static final int IMAGE_SIZE_SMALL = 20;

        public static final int IMAGE_SIZE_MIDDLE = 60;

        public static final int IMAGE_SIZE_LARGE = 80;

        public static final int IMAGE_SIZE_ORIGNAL = 100;
    }

    public static class MatchTimelineEventType {
        public static final int EVENTTYPE_GOAL= 0;
        public static final int EVENTTYPE_ASSIST= 1;
        public static final int EVENTTYPE_GOAL_DIAN= 4;
        public static final int EVENTTYPE_YELLOW_CARD = 7;
        public static final int EVENTTYPE_2YELLOW_TO_RED = 9;//两黄被罚下
        public static final int EVENTTYPE_TO_RED = 10;//红牌直接被罚下
        public static final int EVENTTYPE_SUBSTITUTES_DOWN = 11;
        public static final int EVENTTYPE_SUBSTITUTES_UP = 12;
        public static final int EVENTTYPE_DEADLINESS = 16;//致命失误导致失球
    }
}
