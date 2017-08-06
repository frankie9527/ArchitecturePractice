package com.jyh.rest.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jyh.rest.MainActivity;
import com.jyh.rest.R;
import com.jyh.rest.presenter.SplashPresenter;
import com.jyh.rest.presenter.impl.SplashPresenterImpl;
import com.jyh.rest.utils.StatusBarCompat;
import com.jyh.rest.utils.UIUtils;
import com.jyh.rest.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/10.
 */

public class SplashActivity extends AppCompatActivity implements SplashView {
    @BindView(R.id.splash_image)
    ImageView splash_image;
    @BindView(R.id.splash_slogan)
    TextView splash_slogan;
    @BindView(R.id.splash_copyright)
    TextView splash_copyright;
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();
    }




    protected void initData() {
        presenter = new SplashPresenterImpl(this);
        presenter.initialized();
        StatusBarCompat.compat(this, Color.BLACK);
    }



    @Override
    public void go2Main() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void showSplashImg(int id) {
        Glide.with(UIUtils.getContext()).
                load(id).
                placeholder(R.drawable.splash0).centerCrop().error(R.drawable.splash1).
                into(splash_image);
    }

    @Override
    public void showCopyRight(String copyRight) {
        splash_copyright.setText(copyRight);
    }

    @Override
    public void showSlogan(String slogan) {
        splash_slogan.setText(slogan);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
