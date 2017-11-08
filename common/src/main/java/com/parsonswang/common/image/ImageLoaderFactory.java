package com.parsonswang.common.image;

import com.parsonswang.common.monitor.IMonitor;
import com.parsonswang.common.monitor.MtaMonitorImpl;

/**
 * Created by parsonswang on 2017/10/17.
 */

public enum ImageLoaderFactory {
    INSTANCE;

    public ImageLoader getImageLoaderFactory() {
        return new GlideImageLoader();
    }
}
