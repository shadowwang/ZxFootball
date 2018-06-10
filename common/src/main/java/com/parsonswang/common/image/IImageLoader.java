package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载接口
 * Created by parsonswang on 2017/11/7.
 */

public interface IImageLoader {

    void loadImage(Context context, ImageView imageView, String url, int defaultResId);

    void loadRoundImage(Context context, ImageView imageView, String url, int defaultResId);
}
