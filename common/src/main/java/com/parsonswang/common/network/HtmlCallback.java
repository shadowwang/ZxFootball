package com.parsonswang.common.network;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by wangchun on 2017/12/23.
 */

public abstract class HtmlCallback extends ResonseCallback<String> {

    @Override
    protected String parseResponse(Response response) throws IOException {
        return response.body().string();
    }
}
