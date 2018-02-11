package com.parsonswang.common.image;

/**
 * Created by parsonswang on 2017/10/17.
 */

public enum ImageLoaderFactory {
    INSTANCE;

    public IImageLoader getImageLoaderFactory() {
        return new GlideImageLoader();
    }
}
