package com.yinghai.piaowan.Impl;

import android.content.Context;
import android.widget.ImageView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 11:52
 *         Describe：Banner图片加载的接口实现
 */

public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (path instanceof Integer) {
            ImageLoaderUtils.displayFromDrawable(context,(Integer) path, imageView);
        } else {
            ImageLoaderUtils.loadImage(context, imageView,(String) path);
        }
    }

}