package com.parsonswang.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/10/26.
 */

public abstract class BaseFragment extends Fragment {

    private boolean isViewCreate;
    private boolean isFirstVisible = true;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreate = true;
        Timber.i("----onViewCreated----");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreate && isFirstVisible) {
            isFirstVisible = false;
            onFragmentFirstVisible(true);
        }
        Timber.i("----onViewCreated----" + isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstVisible = true;
        isViewCreate = false;
        Timber.i("----onDestroyView----");
    }

    protected abstract void onFragmentFirstVisible(boolean isVisible);
}
