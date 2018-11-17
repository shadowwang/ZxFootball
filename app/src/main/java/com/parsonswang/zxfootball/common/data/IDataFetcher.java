package com.parsonswang.zxfootball.common.data;

import com.parsonswang.common.network.ResonseCallback;

import java.util.LinkedHashMap;

/**
 * 获取数据的基础接口
 * Created by parsonswang on 2017/10/20.
 */

public interface IDataFetcher<T extends ResonseCallback> {

    void fetchData(T callback, LinkedHashMap<String, String> params);
}
