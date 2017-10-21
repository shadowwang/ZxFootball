package com.parsonswang.zxfootball.matches;

import com.parsonswang.common.network.JsonCallback;
import com.parsonswang.zxfootball.common.IDataFetcher;

/**
 * 从服务器获取比赛数据
 * Created by parsonswang on 2017/10/20.
 */

public class MatchesRemoteDataFetcher implements IDataFetcher<JsonCallback>{

    private String mUrl;

    public MatchesRemoteDataFetcher(String url) {
        this.mUrl = url;
    }

    @Override
    public void fetchData(JsonCallback callback, Object... params) {

    }

}
