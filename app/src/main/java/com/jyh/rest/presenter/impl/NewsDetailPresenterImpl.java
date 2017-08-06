package com.jyh.rest.presenter.impl;

import android.app.Activity;

import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.NewsDetailBean;
import com.jyh.rest.interactor.NewsDetailInteractor;
import com.jyh.rest.interactor.impl.NewsDetailInteractorImpl;
import com.jyh.rest.listener.NewsDetailRequestCallBack;
import com.jyh.rest.presenter.NewsDetailPresenter;
import com.jyh.rest.ui.activity.NewsDetailActivity;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.view.NewsDetailView;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2016/10/29.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsDetailRequestCallBack {
    private NewsDetailView view;
    private NewsDetailInteractor interactor;
    private String docid;

    public NewsDetailPresenterImpl(NewsDetailView view, String docid) {
        this.view = view;
        this.docid = docid;
        interactor = new NewsDetailInteractorImpl(this);
    }

    @Override
    public void initialized() {
        view.showLoading();
        interactor.loadNewsDetail(docid);
        interactor.isCollectionChoice(docid);
    }

    @Override
    public void collectionChoice(NewsBean bean) {
        view.showLoading();
        interactor.collectionChoice(bean);
    }


    @Override
    public void onLoadNewsDetailSuccess(NewsDetailBean bean) {
        view.hideLoading();
        view.onLoadNewsDetailSuccess(bean);
    }

    @Override
    public void onLoadNewsDetailError(String error) {
        view.hideLoading();
        view.showError(error);
    }

    @Override
    public void onCollectionSuccess() {
        view.hideLoading();
        view.onCollectionNews();
    }

    @Override
    public void onCollectionCancel() {
        view.hideLoading();
        view.onCancelCollectionNews();
    }
}
