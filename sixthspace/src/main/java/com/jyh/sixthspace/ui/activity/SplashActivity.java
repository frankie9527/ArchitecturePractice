package com.jyh.sixthspace.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.ActivitySplashBinding;
import com.jyh.sixthspace.uilibrary.base.BaseActivity;


;

/**
 * Created by Administrator on 2017/4/3.
 */

public class SplashActivity extends BaseActivity {
    ActivitySplashBinding bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initView();
    }

    private void initView() {
        Glide.with(this).load(R.drawable.splash).into(bind.splashImage);
        bind.splashParent.postDelayed(new Runnable() {
            @Override
            public void run() {
                go2Main();
            }
        },500);

    }
    public void go2Main(){
        Intent intent =new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
