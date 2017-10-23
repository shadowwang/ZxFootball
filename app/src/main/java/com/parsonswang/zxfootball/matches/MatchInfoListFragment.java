package com.parsonswang.zxfootball.matches;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;

/**
 * Created by wangchun on 2017/10/23.
 */

public class MatchInfoListFragment extends Fragment {

    public static MatchInfoListFragment newInstance(HeaderTabTitle.TabInfo tabInfo) {
        return new MatchInfoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchinfo_list, container, false);
        return view;
    }
}
