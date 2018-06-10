package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by parsonswang on 2017/11/7.
 */

public class GlideImageLoader implements IImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int defaultResId) {
        GlideApp.with(context).load(url).placeholder(defaultResId).into(imageView);
    }

    @Override
    public void loadRoundImage(Context context, ImageView imageView, String url, int defaultResId) {
        RequestOptions roundOptions = new RequestOptions()
                .transform(new CircleCrop());
        GlideApp.with(context).load(url).apply(roundOptions).placeholder(defaultResId).into(imageView);
    }


}
