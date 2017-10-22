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

    private MatchContract.IMatchPresenter matchPresenter;

    public MatchModel(MatchContract.IMatchPresenter matchPresenter) {
        this.matchPresenter = matchPresenter;
    }

    public void getMatchInfoDatas(String competionId, String dateBetween, MachesFetchDataCallback machesFetchDataCallback) {
        if (matchPresenter == null) {
            Timber.e("getMatchInfoDatas fail because matchPresenter init fail!!!");
            return;
        }

        LinkedHashTreeMap<String, String> paramsMap = new LinkedHashTreeMap<>();
        paramsMap.put("comeptitionId", competionId);
        paramsMap.put("date", dateBetween);

        DataFetchFactory.getInstance()
                .getDataFetcher(Constant.NetworkProtocolConstant.MATCH_QUERY_URL)
                .fetchData(machesFetchDataCallback, paramsMap);
    }
}
