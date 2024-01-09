package com.jyh.sixthspace.live.ui.adapter;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.jyh.sixthspace.live.ui.activity.LiveActivity;
import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;


import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 *
 * 直播主界面推荐轮播图
 */

public class LiveMainCarouselViewPagerAdapter extends PagerAdapter {
    List<HomeCarousel> list;
    ImageView[] imgs;
    public LiveMainCarouselViewPagerAdapter() {
        imgs=new ImageView [0];
    }

    @Override
    public int getCount() {
        if (list==null)
            return 0;
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
            return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs[position]);
    }
    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(container.getContext());
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, list.get(position).getTv_pic_url());
        imgs[position]=imageView;
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LiveActivity.class);
                intent.putExtra("Room_id", list.get(position).getRoom().getRoom_id());
                Log.e("zcjyh","Room_id="+list.get(position).getRoom().getRoom_id());
                view.getContext().startActivity(intent);
            }
        });
        return imageView;
    }


    public void setData(List<HomeCarousel> list) {
        this.list = list;
        imgs=new ImageView [list.size()];
        notifyDataSetChanged();
    }
}
