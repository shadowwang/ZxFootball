package com.parsonswang.zxfootball.data;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.R;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class DataFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("DataFragment---onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("DataFragment---onCreateView");
        return inflater.inflate(R.layout.fragment_datas, container, false);
    }
}
