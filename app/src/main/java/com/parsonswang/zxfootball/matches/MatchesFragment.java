package com.parsonswang.zxfootball.matches;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parsonswang.zxfootball.R;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchesBean;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/13.
 */

public class MatchesFragment extends Fragment implements MatchContract.IMatchView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_matches, container, false);
        new MatchPresenter(this).start();
        return view;
    }

    @Override
    public void showHeaderTabTitle(HeaderTabTitle headerTabTitle) {
        Timber.i("headerTabTitle|: " + headerTabTitle);
    }

    @Override
    public void showMatchInfoList(MatchesBean matchesBean) {

    }
}
