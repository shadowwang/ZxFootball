package com.parsonswang.common.network;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.parsonswang.common.BuildConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Dns;
import okhttp3.OkHttpClient;

/**
 * okhttp工具类
 * Created by parsonswang on 2017/10/16.
 */

public class OkHttpUtil {

    private static final int CONNECT_TIMEOUT = 15;

    private static final int READ_TIMEOUT = 10;

    private static volatile OkHttpClient mOkHttpClient;

    private static OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .dns(new Dns() {
                    @Override
                    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                        List<InetAddress> addresses = new ArrayList<InetAddress>();
                        String ipsStr = DnsUtil.getIpByHost(hostname);
                        addresses.add(InetAddress.getByName(ipsStr));
                        return addresses;
                    }
                });

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }

    public static OkHttpClient getInstance() {
        if (mOkHttpClient == null) {
            mOkHttpClient = initOkHttpClient();
        }
        return mOkHttpClient;
    }

    public static HttpGetBuilder get() {
        return new HttpGetBuilder();
    }

}
