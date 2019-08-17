package com.parsonswang.common.base;

import android.content.Context;
import androidx.fragment.app.Fragment;

/**
 * Created by parsonswang on 2018/2/6.
 */

public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
