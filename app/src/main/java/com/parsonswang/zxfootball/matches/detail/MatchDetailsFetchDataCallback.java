package com.parsonswang.zxfootball.matches.detail;


import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.zxfootball.matches.MatchContract;

import okhttp3.Call;
import timber.log.Timber;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailsFetchDataCallback extends HtmlCallback{

    private MatchContract.IMatchDetailView matchDetailView;

    public MatchDetailsFetchDataCallback(MatchContract.IMatchDetailView matchDetailView) {
        this.matchDetailView = matchDetailView;
    }

    @Override
    protected void onSucess(String s) {
        matchDetailView.showMatchProcess(s);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
