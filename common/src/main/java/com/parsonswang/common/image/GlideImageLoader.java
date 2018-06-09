package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import timber.log.Timber;

/**
 * Created by parsonswang on 2017/11/7.
 */

public class GlideImageLoader implements IImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int defaultResId) {
        GlideApp.with(context).load(url).placeholder(defaultResId).into(imageView);
    }

    @Override
    public void loadRoundImage(Context context, ImageView imageView, String url, int defaultResId, int radius) {
        RequestOptions roundOptions = new RequestOptions()
                .transform(new RoundedCorners(radius));
        GlideApp.with(context).load(url).apply(roundOptions).placeholder(defaultResId).into(imageView);
    }


}
