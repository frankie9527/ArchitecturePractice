package com.jyh.sixthspace.viewmodel;


import android.content.Intent;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;
import com.jyh.sixthspace.ui.activity.RecommendMovieInfosActivity;


/**
 * Created by Administrator on 2017/4/18.
 */

public class RecommendRecyclerItemViewModel extends BaseObservable {
    private VideoInfo info;

    public RecommendRecyclerItemViewModel(VideoInfo info) {
        this.info = info;

    }

    public String getTitle() {
        return info.title;
    }

    public String getImgUrl() {
        return info.pic;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, url);
    }

    public void setData(VideoInfo info) {
        this.info = info;
        notifyChange();
    }
    public void go2Activity(View view) {
        Intent intent = new Intent(view.getContext(), RecommendMovieInfosActivity.class);
        intent.putExtra("videoInfo", info);
        view.getContext().startActivity(intent);
    }

}
