package com.parsonswang.zxfootball.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.parsonswang.common.image.Imageloaders;
import com.parsonswang.zxfootball.R;

/**
 * Created by parsonswang on 2017/11/8.
 */

public class ImageUtils {

    public static void loadMatchPlayerAvatar(Context context, ImageView imageView, String url) {
        Imageloaders.loadRoundImage(context, url, imageView, R.drawable.ic_default_player);
    }

}
