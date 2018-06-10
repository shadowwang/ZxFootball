package com.parsonswang.common.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by wangchun on 2018/2/11.
 */

public class Imageloaders {

    public static void loadImage(Context context, String url, ImageView iv, int defaultId) {
        ImageLoaderFactory.INSTANCE.getImageLoaderFactory().loadImage(context, iv, url, defaultId);
    }

    public static void loadRoundImage(Context context, String url, ImageView iv, int defaultId) {
        ImageLoaderFactory.INSTANCE.getImageLoaderFactory().loadRoundImage(context, iv, url, defaultId);
    }

}
