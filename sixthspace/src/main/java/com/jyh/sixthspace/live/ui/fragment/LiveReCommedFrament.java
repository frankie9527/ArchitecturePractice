package com.jyh.sixthspace.live.ui.fragment;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveBinding;
import com.jyh.sixthspace.live.ui.adapter.LiveReCommendMainRecycleAdapter;
import com.jyh.sixthspace.live.view.LiveReCommedView;
import com.jyh.sixthspace.live.viewmodel.LiveReCommedViewModel;

import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.bean.live.HomeFaceScoreColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;
import com.jyh.sixthspace.uilibrary.base.LazyFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class LiveReCommedFrament extends LazyFragment<FragmentLiveBinding> implements LiveReCommedView {
    LiveReCommedViewModel model;
    LiveReCommendMainRecycleAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_live;
    }

    @Override
    public void initViews() {
        adapter = new LiveReCommendMainRecycleAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jyhBinding.recyclerRecommend.setLayoutManager(layoutManager);
        jyhBinding.recyclerRecommend.setHasFixedSize(true);
        jyhBinding.recyclerRecommend.setAdapter(adapter);
    }

    @Override
    public void onFirstUserVisible() {
        model = new LiveReCommedViewModel(this);
    }


    @Override
    public void onLoadCarouselSuccess(List<HomeCarousel> list) {
        adapter.setCarouselData(list);
    }

    @Override
    public void onLoadHotSuccess(List<HomeHotColumn> list) {
        adapter.setHotData(list);
    }

    @Override
    public void onLoadBeautysSuccess(List<HomeFaceScoreColumn> list) {
        adapter.setBeautysData(list);
    }

    @Override
    public void onLoadOtherSuccess(List<HomeRecommendHotCate> list) {
        adapter.setOtherData(list);
    }
}
