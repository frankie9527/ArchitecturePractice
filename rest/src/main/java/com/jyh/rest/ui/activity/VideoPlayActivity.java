package com.jyh.rest.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.utils.StatusBarCompat;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/8.
 */

public class VideoPlayActivity extends BaseActivity {
    StandardGSYVideoPlayer player;
    OrientationUtils orientationUtils;
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
        assert bean != null;
        url = bean.getMp4_url();
        title = bean.getTitle();
        initPlay();
    }


    private void initPlay() {
       player=findViewById(R.id.video_player);
        player.setUp(url, true, title);
        //增加title
        player.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        player.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, player);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        player.setIsTouchWiget(true);
        //设置返回按键功能
        player.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        player.startPlayLogic();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onVideoPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onVideoResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
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
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            player.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        player.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
