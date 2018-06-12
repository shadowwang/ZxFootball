package com.parsonswang.common.network;

import com.parsonswang.common.utils.JsonObjectMap;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/20.
 */

public abstract class JsonCallback<T> extends ResonseCallback<T> {

    @Override
    protected T parseResponse(Response response) throws IOException {
        String string = response.body().string();
        Timber.i(string);

        //得到泛型的class对象
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }

        return JsonObjectMap.getInstance().fromJson(string, entityClass);
    }
}
