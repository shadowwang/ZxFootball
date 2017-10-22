package com.parsonswang.zxfootball.common.data;

import com.parsonswang.common.utils.StringUtils;

/**
 * Created by wangchun on 2017/10/22.
 */

public class DataFetchFactory {

    private DataFetchFactory() {}

    private static class InstanceInnerHolder {
        static DataFetchFactory instance = new DataFetchFactory();
    }

    public static DataFetchFactory getInstance() {
        return InstanceInnerHolder.instance;
    }

    public IDataFetcher getDataFetcher(String url) {
        if (StringUtils.isEmptyString(url)) {
            return new LocalDataFetcherImpl();
        }
        return new RemoteDataFetcherImpl(url);
    }
}
