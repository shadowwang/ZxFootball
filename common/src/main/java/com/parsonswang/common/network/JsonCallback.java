package com.parsonswang.common.network;

import com.parsonswang.common.utils.JsonBinder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * Created by parsonswang on 2017/10/20.
 */

public abstract class JsonCallback<T> extends ResonseCallback<T> {

    @Override
    protected T parseResponse(Response response) throws IOException {
        String string = response.body().string();

        //得到泛型的class对象
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }

        return JsonBinder.getInstance().fromJson(string, entityClass);
    }
}
