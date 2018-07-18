package com.ljcs.cxwl.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author xlei
 * @Date 2018/7/11.
 */

public class GlideUtils {
    public static void loadImgNoCach(Context context, String path, ImageView imageView, RequestOptions options) {
        Glide.with(context).load(path).apply(options).into(imageView);
    }
}
