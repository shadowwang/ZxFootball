package com.parsonswang.common.image;

/**
 * Created by parsonswang on 2017/10/17.
 */

public enum ImageLoaderFactory {
    INSTANCE;

    public ImageLoader getImageLoaderFactory() {
        return new GlideImageLoader();
    }
}
