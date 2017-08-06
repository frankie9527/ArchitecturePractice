package com.jyh.rest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.customview.TabViewpager;
import com.jyh.rest.presenter.MainPresenter;
import com.jyh.rest.presenter.impl.MainPresenterImpl;
import com.jyh.rest.ui.adapter.MainPagerAdapter;
import com.jyh.rest.utils.StatusBarCompat;
import com.jyh.rest.view.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class MainActivity extends BaseActivity implements MainView {
    MainPagerAdapter adapter;
    MainPresenter presenter;
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();


    }

    protected void initViews() {
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        presenter = new MainPresenterImpl(this);
        presenter.initialized();
        vp_main.setAdapter(adapter);
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               if (position==2){
                   StatusBarCompat.compat(MainActivity.this, Color.BLACK);
               }else {
                   StatusBarCompat.compat(MainActivity.this,Color.parseColor("#3F51B5"));
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 选中新闻界面
     */
    @OnClick(R.id.rg_news)
    void Checked_News() {
        vp_main.setCurrentItem(0);

    }

    /**
     * 选中视频界面
     */
    @OnClick(R.id.rg_video)
    void Checked_video() {
        vp_main.setCurrentItem(1);

    }

    @OnClick(R.id.rg_mine)
    void Checked_test() {
        vp_main.setCurrentItem(2);

    }

    @Override
    public void onloadSuccess(List data) {
        vp_main.setOffscreenPageLimit(data.size());
        adapter.setData(data);
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
