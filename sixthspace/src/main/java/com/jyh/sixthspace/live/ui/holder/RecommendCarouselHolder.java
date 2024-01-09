package com.jyh.sixthspace.live.ui.holder;

import android.graphics.Color;

import android.view.View;


import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveCarousesBinding;

import com.jyh.sixthspace.uilibrary.ViewPagerIndicator.ScaleCircleNavigator;
import com.jyh.sixthspace.uilibrary.ViewPagerIndicator.ViewPagerHelper;


/**
 * Created by Administrator on 2017/10/7.
 */

public class RecommendCarouselHolder extends RecyclerView.ViewHolder {
    FragmentLiveCarousesBinding binding;
    ScaleCircleNavigator scaleCircleNavigator;

    public RecommendCarouselHolder(View itemView) {
        super(itemView);
    }

    public void setBind(FragmentLiveCarousesBinding binding) {
        this.binding = binding;
    }

    public FragmentLiveCarousesBinding getBind() {
        return binding;
    }

    public void initIndicator(PagerAdapter pagerAdapter) {
        binding.liveRecommendCarouse.setAdapter(pagerAdapter);
        scaleCircleNavigator = new ScaleCircleNavigator(binding.liveRecommendCarouse.getContext());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        int color = binding.liveRecommendCarouse.getContext().getResources().getColor(R.color.colorPrimary);
        scaleCircleNavigator.setSelectedCircleColor(color);
        //todo
//        binding.indicator.setNavigator(scaleCircleNavigator);
//        ViewPagerHelper.bind(binding.indicator, binding.liveRecommendCarouse);
    }

    public void setCircleCount(int count) {
        scaleCircleNavigator.setCircleCount(count);
    }

}
