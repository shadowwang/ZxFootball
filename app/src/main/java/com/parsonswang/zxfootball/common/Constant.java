package com.parsonswang.zxfootball.common;

import com.parsonswang.zxfootball.common.utils.ConfigUtil;

/**
 * Created by wangchun on 2017/10/22.
 */

public class Constant {

    public static class NetworkProtocolConstant {

        public static final String HTTP_REQUEST_URL = ConfigUtil.getOnlineConfig("server_host", "http://www.tzuqiu.cc/");

        public static final String MATCH_QUERY_URL = HTTP_REQUEST_URL + "matches/queryFixture.json";
    }


    public static class TeamImageSize {
        public static final int IMAGE_SIZE_SMALL = 20;

        public static final int IMAGE_SIZE_MIDDLE = 60;

        public static final int IMAGE_SIZE_LARGE = 80;

        public static final int IMAGE_SIZE_ORIGNAL = 100;
    }
}
