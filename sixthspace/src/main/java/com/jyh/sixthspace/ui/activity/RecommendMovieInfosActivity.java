package com.jyh.sixthspace.ui.activity;


import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.ActivityMovieInfosBinding;
import com.jyh.sixthspace.sdk.base.Request2CallBack;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.bean.movie.VideoRes;
import com.jyh.sixthspace.sdk.utlis.ImgLoadUtils;
import com.jyh.sixthspace.ui.adapter.RecommendRecyclerAdapter;
import com.jyh.sixthspace.uilibrary.base.BaseActivity;
import com.jyh.sixthspace.viewmodel.MovieInfosViewModel;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;



/**
 * Created by Administrator on 2017/4/20.
 * 电影详情界面
 * 推荐界面点击进入的
 */

public class RecommendMovieInfosActivity extends BaseActivity implements Request2CallBack<VideoRes,List<VideoInfo>> {
    private ActivityMovieInfosBinding binding;
    private VideoInfo info;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private MovieInfosViewModel model;
    private RecommendRecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_infos);
        jcVideoPlayerStandard = binding.videoplayer;
        jcVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jcVideoPlayerStandard.titleTextView.setVisibility(View.GONE);

        binding.recyclerMovieRecommend.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerAdapter = new RecommendRecyclerAdapter();
        binding.recyclerMovieRecommend.setAdapter(recyclerAdapter);


    }

    private void initData() {
        info = (VideoInfo) getIntent().getSerializableExtra("videoInfo");
        model = new MovieInfosViewModel(info,this);
        binding.setModel(model);
    }


    @Override
    public void onBackPressed() {
        if (jcVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jcVideoPlayerStandard.releaseAllVideos();
    }
    @Override
    public void onRequestSuccess(VideoRes videoRes, List<VideoInfo> videoInfos) {
        jcVideoPlayerStandard.setUp(videoRes.getVideoUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoRes.title);
        ImgLoadUtils.loadImgByUrlcenterCrop(jcVideoPlayerStandard.thumbImageView, info.pic);
        recyclerAdapter.setData(videoInfos);
    }
}
