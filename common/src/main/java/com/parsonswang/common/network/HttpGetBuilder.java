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

        final Request request = requestBuilder.url(url)
                .removeHeader("User-Agent").addHeader("User-Agent",getUserAgent())
                .removeHeader("Referer").addHeader("Referer", urlStr)
                .removeHeader("Cookie").addHeader("Cookie", getCookie()).build();

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

    private static String getCookie() {
        return "announceId=20180103001; Hm_lvt_b83b828716a7230e966a4555be5f6151=1528788995,1529587059,1531112637; _ga=GA1.2.345788051.1531112638; _gid=GA1.2.535313772.1531112638; __gads=ID=58c43d3144f68c67:T=1531112637:S=ALNI_MZmPNB5RdlQ6nkcrvSgECP2-A3h1Q; JSESSIONID=A8ED2CB5DC7B9B3F83CEFF4C98A9DD75; Hm_lpvt_b83b828716a7230e966a4555be5f6151=1531285137; PHPSESSID=4c08c5fvlb8q38fdl6bj5hg254";
    }
}
