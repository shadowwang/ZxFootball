package com.parsonswang.zxfootball.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.ZxApplication;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class NewsFragment extends FlutterFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("---NewsFragment--- onCreate");
    }

    @Nullable
    @Override
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return ((ZxApplication) context.getApplicationContext()).flutterEngine;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("---NewsFragment--- onCreateView");
        final View view = inflater.inflate(R.layout.fragment_price, container, false);
        FlutterView flutterView = new FlutterView(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout flContainer = view.findViewById(R.id.fl_container);
        flContainer.addView(flutterView, lp);

        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(getFlutterEngine());
        return view;
//        return inflater.inflate(R.layout.fragment_price, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.i("---NewsFragment--- onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("---NewsFragment--- onDestroy");
    }
}
