package com.parsonswang.common.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by parsonswang on 2017/10/20.
 */

public abstract class ResonseCallback<T> {

    protected abstract T parseResponse(Response response) throws IOException;

    protected abstract void onSuccess(T t);

    protected abstract void onFail(Call call, String reson);

}
