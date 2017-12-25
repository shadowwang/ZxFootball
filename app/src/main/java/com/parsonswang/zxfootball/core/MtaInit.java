package com.parsonswang.zxfootball.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

import timber.log.Timber;

/**
 * MTA的初始化
 * Created by parsonswang on 2017/10/16.
 */

public class MtaInit {

    /**
     * 初始化mta
     *
     * @param c 上下文相关对象
     */
    public static void initMta(Context c) {
        Timber.i("initMta: ");
        try {

            final ApplicationInfo applicationInfo = c.getPackageManager()
                    .getApplicationInfo(c.getPackageName(), PackageManager.GET_META_DATA);
            String appKey = applicationInfo.metaData.getString("TA_APPKEY");
            Timber.i("appKey: " + appKey);
            //String channelID = AppInfo.getAppMetaData(c, "InstallChannel", "txEnter");
            //使用walle多渠道打包
//            String channelID = StringUtils.isEmpty(channel)?"zxfootball":channel;
            String channelID = applicationInfo.metaData.getString("InstallChannel");
            Timber.i("channelID: " + channelID);
            StatConfig.setInstallChannel(channelID);
            StatService.startStatService(c, appKey, com.tencent.stat.common.StatConstants.VERSION);
        } catch (Exception ex) {
            Timber.e("Init MTA fail: " + ex.getMessage());
        }
    }
}
