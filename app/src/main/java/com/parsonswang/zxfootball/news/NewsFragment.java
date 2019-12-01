package com.parsonswang.zxfootball.news;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.flutter.facade.Flutter;
import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class NewsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("---NewsFragment--- onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("---NewsFragment--- onCreateView");
        return Flutter.createView(getActivity(), getLifecycle(), "main_page");
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
