package com.parsonswang.zxfootball.common;

import com.parsonswang.common.network.ResonseCallback;

/**
 * 获取数据的基础接口
 * Created by parsonswang on 2017/10/20.
 */

public interface IDataFetcher<T extends ResonseCallback> {

    void fetchData(T callback, Object... params);
}
