package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/11/7.
 */

public class GlideImageLoader implements IImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int defaultResId) {
        Timber.i(url);
        GlideApp.with(context).load(url).placeholder(defaultResId).into(imageView);
    }
}
