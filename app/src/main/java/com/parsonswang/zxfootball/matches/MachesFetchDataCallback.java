package com.parsonswang.zxfootball.matches;

import com.parsonswang.common.network.JsonCallback;
import com.parsonswang.zxfootball.bean.MatchesBean;

import okhttp3.Call;

/**
 * Created by parsonswang on 2017/10/20.
 */

public class MachesFetchDataCallback<T> extends JsonCallback<MatchesBean> {

    private MatchContract.IMatchInfoView matchView;

    public MachesFetchDataCallback(MatchContract.IMatchInfoView matchView) {
        this.matchView = matchView;
    }

    @Override
    protected void onSuccess(MatchesBean matchesBean) {
        matchView.showMatchInfoList(matchesBean);
    }

    @Override
    protected void onFail(Call call, String reson) {

    }
}
