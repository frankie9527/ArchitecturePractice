package com.jyh.sixthspace.viewmodel;


import android.content.Intent;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;
import com.jyh.sixthspace.ui.activity.MoviesTpyeListActivity;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MoviesRecyclerItemViewModel extends BaseObservable{
    private VideoInfo info;


    public MoviesRecyclerItemViewModel(VideoInfo info) {
        this.info=info;

    }
    public String getTitle(){
        return info.title;
    }
    public int getImgUrl() {
        return info.localPic;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, int url) {
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, url);
    }
    public void setData(VideoInfo info){
        this.info=info;
        notifyChange();
    }

    public void go2Activity(View view) {
        Intent intent=new Intent(view.getContext(), MoviesTpyeListActivity.class);
        intent.putExtra("videoInfo", info);
        view.getContext().startActivity(intent);
    }
}
