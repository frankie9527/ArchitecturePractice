package com.jyh.sixthspace.live.ui.activity;




import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.ActivityLiveBinding;
import com.jyh.sixthspace.live.danmu.utils.DanmuProcess;
import com.jyh.sixthspace.live.viewmodel.LiveDanMuModel;
import java.util.Observable;
import java.util.Observer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import master.flame.danmaku.ui.widget.DanmakuView;
import com.jyh.sixthspace.uilibrary.base.BaseActivity;

/**
 * Created by Administrator on 2017/10/9.
 */

public class  LiveActivity extends BaseActivity implements Observer {
    private ActivityLiveBinding binding;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private String Room_id;
    private LiveDanMuModel muModel;
    private DanmakuView danmakuView;
    /**
     * 弹幕
     */
    private DanmuProcess mDanmuProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live);
        initView();
        initData();
    }


    private void initView() {
        jcVideoPlayerStandard = binding.videoplayer;
        jcVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jcVideoPlayerStandard.titleTextView.setVisibility(View.GONE);
        danmakuView=binding.svDanmaku;

    }

    private void initData() {
        Room_id = getIntent().getExtras().getString("Room_id");
        muModel = new LiveDanMuModel();
        muModel.addObserver(this);
        muModel.getLiveUrl(Room_id);

        mDanmuProcess = new DanmuProcess(this, danmakuView, Integer.valueOf(Room_id));
        mDanmuProcess.start();
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
        if (danmakuView != null) {
            danmakuView.pause();
        }
    }

    @Override
    public void update(Observable observable, final Object obj) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String str = (String) obj;
                Log.e("zcjyh", "show" + str);
                jcVideoPlayerStandard.setUp(str
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                jcVideoPlayerStandard.startVideo();

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (danmakuView != null && mDanmuProcess != null) {
            danmakuView.restart();
            mDanmuProcess.start();
        }
    }


    @Override
    protected void onDestroy() {
        if (jcVideoPlayerStandard != null) {
            //        释放资源releaseAllVideos
            jcVideoPlayerStandard.releaseAllVideos();

        }
        mDanmuProcess.finish();
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView.clear();
        }
        super.onDestroy();
    }

}
