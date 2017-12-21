package com.parsonswang.zxfootball.matches;

import com.google.gson.internal.LinkedHashTreeMap;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.common.Constant;
import com.parsonswang.zxfootball.common.data.DataFetchFactory;

import timber.log.Timber;

/**
 * Created by wangchun on 2017/10/22.
 */

public class MatchModel {

    public void getMatchInfoDatas(String competionId, String dateBetween, MachesFetchDataCallback machesFetchDataCallback) {

        LinkedHashTreeMap<String, String> paramsMap = new LinkedHashTreeMap<>();
        paramsMap.put("comeptitionId", competionId);
        paramsMap.put("date", dateBetween);

        DataFetchFactory.getInstance()
                .getDataFetcher(Constant.NetworkProtocolConstant.MATCH_QUERY_URL)
                .fetchData(machesFetchDataCallback, paramsMap);
    }
}
