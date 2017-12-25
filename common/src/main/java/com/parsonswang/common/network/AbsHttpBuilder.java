package com.parsonswang.common.network;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import okhttp3.Request;

/**
 * Created by parsonswang on 2017/10/19.
 */

public abstract class AbsHttpBuilder<T extends AbsHttpBuilder> {

    protected String urlStr;
    protected LinkedHashMap<String, String> params;
    protected LinkedHashMap<String, String> headers;

    public T url(String url) {
        this.urlStr = url;
        return (T) this;
    }

    public T headers(LinkedHashMap<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addParams(String key, String value) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return (T) this;
    }

    public T addHeaders(String key, String value) {
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        headers.put(key,value);
        return (T) this;
    }

    protected void addHeadser(Request.Builder requestBuilder) {
        if (headers != null) {
            Set set = headers.keySet();
            for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
                String key = iter.next();
                requestBuilder.addHeader(key,  headers.get(key));
            }
        }
    }

    public abstract RequestCall build();
}
