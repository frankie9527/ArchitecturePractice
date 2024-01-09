package com.jyh.sixthspace.sdk.utlis;



import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.sdk.SixthSpaceSdk;


/**
 * Created by Administrator on 2017/4/27.
 */

public class ImgLoadUtils {
    public static void loadImgByUrl(ImageView imageView, String url) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).into(imageView);
    }

    public static void loadImgByUrlfitCenter(ImageView imageView, String url) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).fitCenter().placeholder(R.color.alpha_15_white).into(imageView);
    }

    public static void loadImgByUrlfitCenter(ImageView imageView, int url) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).fitCenter().placeholder(R.color.alpha_15_white).into(imageView);
    }

    public static void loadImgByUrlcenterCrop(ImageView imageView, String url) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).centerCrop().placeholder(R.color.alpha_15_white).into(imageView);
    }

    public static void loadImgByUrlcenterCrop(ImageView imageView, int url) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).centerCrop().placeholder(R.color.alpha_15_white).into(imageView);
    }

    public static void loadImgTransform(ImageView imageView, String url, int num) {
        Glide.with(SixthSpaceSdk.getContext()).load(url).transform(new CornersTransform (imageView.getContext(),num)).centerCrop().placeholder(R.color.alpha_15_white).into(imageView);
    }
}
