package com.parsonswang.common.monitor;

import android.content.Context;

import com.parsonswang.common.utils.MtaUitls;

import java.util.Map;

/**
 * Created by parsonswang on 2017/10/17.
 */

public class MtaMonitorImpl implements IMonitor{

    private MtaUitls mMtaUitls;

    public MtaMonitorImpl() {
        this.mMtaUitls = new MtaUitls();
    }

    @Override
    public void crashMonitor(Context context) {
        mMtaUitls.reportCrash(context);
    }

    @Override
    public void netSpeedMonitor(Map<String, Integer> map) {

    }
}
