package com.parsonswang.zxfootball.matches.detail;

import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.zxfootball.matches.MatchContract;

import okhttp3.Call;

/**
 * Created by parsonswang on 2018/2/5.
 */

public class MatchStatFetchDataCallback extends HtmlCallback {

    private MatchContract.IMatchDetailView matchDetailView;

    public MatchStatFetchDataCallback(MatchContract.IMatchDetailView matchDetailView) {
        this.matchDetailView = matchDetailView;
    }

    @Override
    protected void onSuccess(String s) {

    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
