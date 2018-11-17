package com.parsonswang.zxfootball.common.data;

import com.parsonswang.common.network.HttpGetBuilder;
import com.parsonswang.common.network.JsonCallback;
import com.parsonswang.common.network.OkHttpUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * 从服务器获取比赛数据
 * Created by parsonswang on 2017/10/20.
 */

public class RemoteJsonDataFetcherImpl implements IDataFetcher<JsonCallback>{

    private String mUrl;

    public RemoteJsonDataFetcherImpl(String url) {
        this.mUrl = url;
    }

    @Override
    public void fetchData(JsonCallback callback, LinkedHashMap<String, String> params) {
        HttpGetBuilder httpGetBuilder = OkHttpUtil.get().url(mUrl);

        if (params != null) {
            for (Map.Entry<String, String> entry :params.entrySet()) {
                httpGetBuilder.addParams(entry.getKey(), entry.getValue());
            }
        }
        Timber.i("fetchData: " + mUrl);

        httpGetBuilder.build().execute(callback);
    }
}
