package com.jyh.rest.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jyh.rest.R;

import static com.jyh.rest.R.mipmap.logo_rest;


/**
 * Created by sunxx on 2016/5/30.
 */
public class GlideUtils {



    public static void loadPic(String str, ImageView img) {
        Glide.with(UIUtils.getContext()).
                load(str).
                centerCrop().placeholder(R.mipmap.logo_rest).into(img);


    }

    public static void loadPicBrow(String str, ImageView img) {
        int width = UIUtils.getNewsPicBrowSize()[0];
        int height = UIUtils.getNewsPicBrowSize()[1];
        Glide.with(UIUtils.getContext()).load(str).asBitmap().override(width, height)
                .placeholder(R.mipmap.ic_launcher)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(img);

    }

    public static void loadCircular(final ImageView img,String url) {
        Glide.with(UIUtils.getContext())
                .load(url)
                .asBitmap()
                .error(logo_rest)
                .into(new BitmapImageViewTarget(img) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(
                                        UIUtils.getResources(),
                                        resource);
                        circularBitmapDrawable.setCircular(true);
                        img.setImageDrawable(circularBitmapDrawable);
                    }
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(
                                        UIUtils.getResources(),
                                        ((BitmapDrawable) errorDrawable).getBitmap());
                        circularBitmapDrawable.setCircular(true);
                        img.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    ;

}
