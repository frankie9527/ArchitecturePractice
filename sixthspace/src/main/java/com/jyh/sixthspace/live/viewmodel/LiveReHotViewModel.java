package com.jyh.sixthspace.live.viewmodel;

import android.content.Intent;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.jyh.sixthspace.live.ui.activity.LiveActivity;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;

/**
 * Created by Administrator on 2017/10/9.
 */

public class LiveReHotViewModel extends BaseObservable {
    private HomeHotColumn info;

    private HomeRecommendHotCate.RoomListEntity entity;

    public String getImgUrl() {
        return info.getRoom_src();
    }

    public String getRoomName() {
        return info.getRoom_name();
    }

    public String getNickName() {
        return info.getNickname();
    }

    public String getOnLinNum() {
        return info.getOnline() + "";
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, url);
    }

    public void setData(HomeHotColumn info) {
        this.info = info;
        notifyChange();
    }

    public void setData(HomeRecommendHotCate.RoomListEntity entity) {
        this.entity = entity;
        info = new HomeHotColumn();
        info.setRoom_src(entity.getRoom_src());
        info.setNickname(entity.getNickname());
        info.setRoom_id(entity.getRoom_id());
        info.setOnline(entity.getOnline());
        notifyChange();
    }

    public void go2Activity(View view) {
        Intent intent = new Intent(view.getContext(), LiveActivity.class);
        intent.putExtra("Room_id", info.getRoom_id());
        Log.e("zcjyh", "Room_id=" + info.getRoom_id());
        view.getContext().startActivity(intent);

    }
}
