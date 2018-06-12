package com.parsonswang.common.network;

import android.net.Uri;
import android.os.Build;
import android.webkit.WebSettings;

import com.parsonswang.common.utils.StringUtils;

import java.util.Set;

import okhttp3.Request;
import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/20.
 */

public class HttpGetBuilder extends AbsHttpBuilder<HttpGetBuilder> {

    @Override
    public RequestCall build() {
        final Request.Builder requestBuilder = new Request.Builder();

        addHeadser(requestBuilder);

        final String url = generateUrl(urlStr);
        Timber.i(url);

        final Request request = requestBuilder.url(url).removeHeader("User-Agent").addHeader("User-Agent",getUserAgent()).build();

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

    private static String getUserAgent() {
        return "Mozilla/5.0 (Linux; Android 6.0.1; MI 4LTE Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko)" +
                "Version/4.0 Chrome/51.0.2704.81 Mobile Safari/537.36";
    }
}
