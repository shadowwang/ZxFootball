package com.parsonswang.zxfootball.matches.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.common.base.BaseLazyLoadFragment;
import com.parsonswang.zxfootball.R;

/**
 * Created by wangchun on 2018/2/6.
 */

public class MatchProcessFragment extends BaseLazyLoadFragment {

    @Override
    protected void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_process, container, false);
        return view;
    }
}
