package com.parsonswang.common.network;


import com.parsonswang.common.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by parsonswang on 2017/10/16.
 */

public class DnsUtil {

    public static String getIpByHost(String host) {
        if (StringUtils.isEmptyString(host)) {
            return "";
        }
        String url = "http://119.29.29.29/d?dn=" + host;
        return httpGet(url);
    }

    private static String httpGet(String url) {
        String responseBody = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null;
        try {
            URL getUrl = new URL(url);
            urlConnection = (HttpURLConnection)getUrl
                    .openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int respCode = urlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == respCode) {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String readLine = "";
                StringBuilder response = new StringBuilder();
                while (null != (readLine = bufferedReader.readLine())) {
                    response.append(readLine);
                }

                bufferedReader.close();
                responseBody = response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return responseBody;
    }
}
