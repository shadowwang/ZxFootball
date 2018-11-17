package com.parsonswang.zxfootball.common.data;

import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.network.HttpGetBuilder;
import com.parsonswang.common.network.OkHttpUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * 从服务器获取比赛数据
 * Created by parsonswang on 2017/12/23.
 */

public class RemoteRawDataFetcherImpl implements IDataFetcher<HtmlCallback>{

    private String mUrl;

    public RemoteRawDataFetcherImpl(String url) {
        this.mUrl = url;
    }

    @Override
    public void fetchData(HtmlCallback callback, LinkedHashMap<String, String> params) {
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
