package com.parsonswang.zxfootball.matches.detail;


import com.parsonswang.common.network.HtmlCallback;

import okhttp3.Call;
import timber.log.Timber;

/**
 * Created by wangchun on 2017/12/23.
 */

public class MatchDetailsFetchDataCallback extends HtmlCallback{

    @Override
    protected void onSucess(String s) {
        Timber.i(s);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
