package com.parsonswang.common.network;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by parsonswang on 2017/10/20.
 */

public class RequestCall {

    private Call mCall;
    private Handler mHandler;

    public RequestCall(Call call) {
        this.mCall = call;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void execute(final ResonseCallback callback) {
        if (mCall == null) {
            return;
        }

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFail(call, e.getLocalizedMessage());
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                if (call.isCanceled()) {
                    RequestCall.this.mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(call, "the request has canceled!");
                            RequestCall.this.mHandler.removeCallbacksAndMessages(null);
                        }
                    });
                    return;
                }

                final Object o = callback.parseResponse(response);
                RequestCall.this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(o);
                        RequestCall.this.mHandler.removeCallbacksAndMessages(null);
                    }
                });
            }
        });
    }
}
