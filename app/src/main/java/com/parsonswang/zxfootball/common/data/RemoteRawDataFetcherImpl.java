package com.parsonswang.zxfootball.common.data;

import com.google.gson.internal.LinkedHashTreeMap;
import com.parsonswang.common.network.HtmlCallback;
import com.parsonswang.common.network.HttpGetBuilder;
import com.parsonswang.common.network.JsonCallback;
import com.parsonswang.common.network.OkHttpUtil;

import java.util.Map;

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
    public void fetchData(HtmlCallback callback, LinkedHashTreeMap<String, String> params) {
        HttpGetBuilder httpGetBuilder = OkHttpUtil.get().url(mUrl);

        if (params != null) {
            for (Map.Entry<String, String> entry :params.entrySet()) {
                httpGetBuilder.addParams(entry.getKey(), entry.getValue());
            }
        }

        httpGetBuilder.build().execute(callback);
    }
}
