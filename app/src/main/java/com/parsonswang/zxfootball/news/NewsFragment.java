package com.parsonswang.zxfootball.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.R;

import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return Flutter.createView(getActivity(), getLifecycle(), "main_page");
    }
}
