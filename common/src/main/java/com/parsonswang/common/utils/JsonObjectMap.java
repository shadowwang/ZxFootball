package com.parsonswang.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * 利用 Gson 来完成 json 和 POJO 之间的转换工作, 采取 data binder 的模式.
 *
 */
public class JsonObjectMap {

    private static final String TAG = JsonObjectMap.class.getName();

    private static JsonObjectMap jsonBinder;

    private Gson gson;

    private JsonObjectMap() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    /**
     * 采用单例模式工作, 主要是后期为了扩展 Gson 对象的功能.
     *
     * @return
     */
    public static JsonObjectMap getInstance() {
        if (jsonBinder == null) {
            jsonBinder = new JsonObjectMap();
        }
        return jsonBinder;
    }

    /**
     * 从 json 字符串生成相应的 java 对象, 注意当转换失败时返回 null.
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            if (jsonString == null) {
                return clazz.newInstance();
            }
            return gson.fromJson(jsonString, clazz);
        } catch (JsonSyntaxException jse) {
            Timber.e(TAG, "form json error.");
        } catch (InstantiationException ie) {
            Timber.e(TAG, clazz.getName() + "clazz new instance instantiation error.");
        } catch (IllegalAccessException iae) {
            Timber.e(TAG, clazz.getName() + "clazz IllegalAccessException error.");
        }catch (NumberFormatException nfe){
            Timber.e(TAG, clazz.getName() + "clazz NumberFormatException error.");
        }
        return null;
    }

    /**
     * 将 java 对象生成 json 格式.
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 从 json 字符串生成相应的 java list 对象, 注意当转换失败时返回一个空的 list.
     *
     * @param jsonString
     * @param type       需要转换的类型. e.g. new TypeToken<List<T>>(){}.getType()
     * @return
     */
    public <T> List<T> fromJson(String jsonString, Type type) {
        if (jsonString == null) {
            return new ArrayList<T>();
        }
        try {
            return gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Timber.e(TAG, "form json error.");
        }
        return new ArrayList<T>();
    }


    public <T> T generticfromJson(String jsonString, Type type) {
        try {
            return gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            Timber.e(TAG, "form json error.");
        }
        return null;
    }

    /**
     * 将 java list 对象生成 json 字符串.
     *
     * @param ts
     * @return
     */
    public <T> String toJson(List<T> ts) {
        return gson.toJson(ts, new TypeToken<List<T>>() {
        }.getType());
    }
}
