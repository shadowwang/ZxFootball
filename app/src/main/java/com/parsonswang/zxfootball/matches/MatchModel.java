package com.parsonswang.zxfootball.matches;

import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.common.data.DataFetchFactory;
import com.parsonswang.zxfootball.matches.detail.MatchDetailsFetchDataCallback;
import com.parsonswang.zxfootball.matches.detail.MatchPlayerInfoCallback;
import com.parsonswang.zxfootball.matches.detail.MatchStatFetchDataCallback;

import java.util.LinkedHashMap;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchModel {

    public void getMatchInfoDatas(String competionId, String dateBetween, MachesFetchDataCallback machesFetchDataCallback) {

        LinkedHashMap<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("comeptitionId", competionId);
        paramsMap.put("date", dateBetween);

        DataFetchFactory.getInstance()
                .getDataFetcher(Constant.NetworkProtocolConstant.MATCH_QUERY_URL, DataFetchFactory.DATA_FETCH_TYPE_JSON)
                .fetchData(machesFetchDataCallback, paramsMap);
    }

    public void getMatchDetailInfo(String comeptionId, MatchDetailsFetchDataCallback callback) {
        String url = String.format(Locale.getDefault(), Constant.NetworkProtocolConstant.MATCH_DETAIL_URL, comeptionId);
        Timber.i(url);

        DataFetchFactory.getInstance()
                .getDataFetcher(url, DataFetchFactory.DATA_FETCH_TYPE_HTML)
                .fetchData(callback, null);
    }

    public void getMatchStatInfo(String comeptionId, MatchStatFetchDataCallback callback) {
        String url = String.format(Locale.getDefault(), Constant.NetworkProtocolConstant.MATCH_STAT_URL, comeptionId);
        Timber.i(url);

        DataFetchFactory.getInstance()
                .getDataFetcher(url, DataFetchFactory.DATA_FETCH_TYPE_HTML)
                .fetchData(callback, null);
    }

    public void getMatchPlayerInfo(String comeptionId, MatchPlayerInfoCallback callback) {
        String url = String.format(Locale.getDefault(), Constant.NetworkProtocolConstant.MATCH_PLAYERINFO_URL, comeptionId);
        Timber.i(url);

        DataFetchFactory.getInstance()
                .getDataFetcher(url, DataFetchFactory.DATA_FETCH_TYPE_HTML)
                .fetchData(callback, null);
    }
}
