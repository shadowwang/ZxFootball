package com.parsonswang.common.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/26.
 */

public abstract class BaseLazyLoadFragment extends BaseFragment {

    private boolean isViewCreate = false;
    private boolean isDataLoaded = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreate = true;
        if (getUserVisibleHint() && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreate && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
        Timber.i("setUserVisibleHint|isVisibleToUser: " + isVisibleToUser + " |isViewCreate: " + isViewCreate + " |isDataLoaded: " + isDataLoaded);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreate = false;
        isDataLoaded = false;
    }

    protected abstract void loadData();
}
