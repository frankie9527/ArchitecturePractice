package com.jyh.sixthspace.viewmodel;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.bean.movie.VideoType;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;
import com.jyh.sixthspace.ui.activity.RecommendMovieInfosActivity;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MoviesTypeListItemViewModel extends BaseObservable {
    private VideoType info;


    public MoviesTypeListItemViewModel(VideoType info) {
        this.info=info;

    }
    public String getTitle(){
        return info.title;
    }
    public String getImgUrl() {
        return info.pic;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, int url) {
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, url);
    }
    public void setData(VideoType info){
        this.info=info;
        notifyChange();
    }

    public void go2Activity(View view) {
        Intent intent = new Intent(view.getContext(), RecommendMovieInfosActivity.class);
        VideoInfo videoInfo=new VideoInfo();
        videoInfo.setDataId(info.dataId);
        intent.putExtra("videoInfo", videoInfo);
        view.getContext().startActivity(intent);

    }
}
