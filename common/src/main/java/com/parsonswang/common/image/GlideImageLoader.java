package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by parsonswang on 2017/11/7.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int defaultResId) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false).placeholder(defaultResId).into(imageView);
    }
}
