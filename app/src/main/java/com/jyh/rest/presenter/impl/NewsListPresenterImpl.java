package com.jyh.rest.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.jyh.rest.RestApplication;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.interactor.NewsListInteractor;
import com.jyh.rest.interactor.impl.NewsListInteractorImpl;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.presenter.NewsListPresenter;
import com.jyh.rest.ui.activity.HoriztonPicBrowActivity;
import com.jyh.rest.ui.activity.NewsDetailActivity;
import com.jyh.rest.ui.adapter.NewsListRecyclerAdapter;
import com.jyh.rest.utils.spUtils;
import com.jyh.rest.view.ListNewsVideoView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class NewsListPresenterImpl implements NewsListPresenter, RequestListCallBack<NewsBean> {
    private ListNewsVideoView view;
    /**
     * 当前加载的角标
     * 初始化是加载头
     * 角标为0
     * 每次下来一下角标+1
     */
    private int current_position = 0;
    private int current_fragment;
    private NewsListInteractor interactor;
    private Boolean isFrist = true;
    private String type;
    private String id;

    public NewsListPresenterImpl(ListNewsVideoView view, int current_fragment) {
        this.view = view;
        this.current_fragment = current_fragment;
        interactor = new NewsListInteractorImpl(this);

    }

    @Override
    public void loadingHead() {
        if (isFrist) {
            view.showLoading();
        }
        if (current_fragment == 0) {
            this.type = "headline";
            this.id = RestApplication.getNewsPosition2type().get(current_fragment);
            interactor.loadNewsList(type, id, 0);
        } else {
            this.type = "list";
            this.id = RestApplication.getNewsPosition2type().get(current_fragment);
            interactor.loadNewsList(type, id, 0);
        }
        current_position = 0;
    }

    @Override
    public void loadingFoot() {
        interactor.loadNewsList(type, id, current_position);
    }

    @Override
    public void go2Activity(NewsBean bean, Activity activity) {
        Intent intent;
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        if (bean.getImgextra()==null){
             intent = new Intent(activity, NewsDetailActivity.class);
            spUtils.put(bean.getDocid(), true);
        }else {
             intent = new Intent(activity, HoriztonPicBrowActivity.class);
        }
        intent.putExtras(bundle);
      activity.startActivity(intent);

    }


    @Override
    public void onLoadHeadSuccess(List<NewsBean> data) {
        view.onloadHeadSuccess(data);
        current_position++;
        if (isFrist) {
            isFrist = false;
        }
    }

    @Override
    public void onloadMoreSuccess(List<NewsBean> data) {
        view.onloadMoreSuccess(data);
        current_position++;
    }

    @Override
    public void onloadHeadError(String error) {
        view.onloadHeadError(error);
    }

    @Override
    public void onloadMoreError(String error) {
        view.showError(error);
    }
}
