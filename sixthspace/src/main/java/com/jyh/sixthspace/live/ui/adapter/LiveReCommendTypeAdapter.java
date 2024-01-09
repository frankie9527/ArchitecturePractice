package com.jyh.sixthspace.live.ui.adapter;


import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/10/9.
 */

public class LiveReCommendTypeAdapter extends RecyclerView.Adapter {
    List<HomeRecommendHotCate> list=new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setData(List<HomeRecommendHotCate> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }
}
