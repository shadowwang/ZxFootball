package com.parsonswang.zxfootball.common;

import com.google.gson.internal.LinkedHashTreeMap;
import com.parsonswang.common.network.ResonseCallback;

/**
 * 获取数据的基础接口
 * Created by parsonswang on 2017/10/20.
 */

public interface IDataFetcher<T extends ResonseCallback> {

    void fetchData(T callback, LinkedHashTreeMap<String, String> params);
}
