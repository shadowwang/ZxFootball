package com.parsonswang.zxfootball.common.data;

import com.parsonswang.common.utils.StringUtils;

/**
 * Created by wangchun on 2017/10/22.
 */

public class DataFetchFactory {

    public static final int DATA_FETCH_TYPE_JSON = 1001;
    public static final int DATA_FETCH_TYPE_HTML = 1002;

    private DataFetchFactory() {}

    private static class InstanceInnerHolder {
        static DataFetchFactory instance = new DataFetchFactory();
    }

    public static DataFetchFactory getInstance() {
        return InstanceInnerHolder.instance;
    }

    public IDataFetcher getDataFetcher(String url, int type) {
        if (StringUtils.isEmptyString(url)) {
            return new LocalDataFetcherImpl();
        }
        switch (type) {
            case DATA_FETCH_TYPE_JSON:
                return new RemoteJsonDataFetcherImpl(url);
            case DATA_FETCH_TYPE_HTML:
            default:
                return new RemoteRawDataFetcherImpl(url);
        }
    }
}
