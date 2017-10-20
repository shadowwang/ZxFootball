package com.parsonswang.common.network;

import android.net.Uri;

import com.facebook.stetho.common.StringUtil;
import com.parsonswang.common.utils.StringUtils;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by parsonswang on 2017/10/20.
 */

public class HttpGetBuilder extends AbsHttpBuilder<HttpGetBuilder> {

    @Override
    public RequestCall build() {
        final Request.Builder requestBuilder = new Request.Builder();

        addHeadser(requestBuilder);

        final String url = generateUrl(urlStr);

        final Request request = requestBuilder.url(url).build();

        return new RequestCall(OkHttpUtil.getInstance().newCall(request));
    }

    private String generateUrl(String url) {
        if (StringUtils.isEmptyString(url)) {
            return "";
        }

        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.appendQueryParameter(key, params.get(key));
            }
        }
        return builder.build().toString();
    }
}
