package com.jyh.rest.ui.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.utils.StatusBarCompat;

import butterknife.ButterKnife;
import tcking.github.com.giraffeplayer.GiraffePlayer;

/**
 * Created by Administrator on 2016/11/8.
 */

public class VideoPlayActivity extends BaseActivity {
    GiraffePlayer player;
    VideoBean bean;
    String url;
    String title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
        initData();
    }



    protected void initData() {
        StatusBarCompat.compat(VideoPlayActivity.this, Color.BLACK);
        bean = (VideoBean) getIntent().getSerializableExtra("bean");
        url = bean.getMp4_url();
        title = bean.getTitle();
        initPlay();
    }


    private void initPlay() {
        player = new GiraffePlayer(this);
        player.play(url);
        player.setTitle(title);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        if (player != null) {
//            player.onConfigurationChanged(newConfig);
//        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
