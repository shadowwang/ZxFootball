package com.parsonswang.zxfootball.network;

import com.parsonswang.zxfootball.utils.StringUtils;

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
        try {
            URL getUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl
                    .openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int respCode = urlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == respCode) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String readLine = null;
                StringBuffer response = new StringBuffer();
                while (null != (readLine = bufferedReader.readLine())) {
                    response.append(readLine);
                }

                bufferedReader.close();
                responseBody = response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
