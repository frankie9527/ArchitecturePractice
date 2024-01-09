package com.jyh.sixthspace.ui.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;
import com.jyh.sixthspace.ui.activity.RecommendMovieInfosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class RecommendViewPagerAdapter extends PagerAdapter {
    List<VideoInfo> listInfo;
    Context mContext;
    ImageView [] imgs;
    public RecommendViewPagerAdapter(Context mContext) {
        this.listInfo = new ArrayList<>();
        this.mContext = mContext;
        imgs=new ImageView [0];
    }

    @Override
    public int getCount() {
        if (listInfo==null)return 0;
        return listInfo.size();
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
        ImageView imageView=new ImageView(mContext);
        ImgLoadUtils.loadImgByUrlcenterCrop(imageView, listInfo.get(position).pic);
        imgs[position]=imageView;
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecommendMovieInfosActivity.class);
                intent.putExtra("videoInfo", listInfo.get(position));
                view.getContext().startActivity(intent);
            }
        });
        return imageView;
    }


    public void setData(List<VideoInfo> list) {
        this.listInfo = list;
        imgs=new ImageView [list.size()];
        notifyDataSetChanged();
    }
}
