package com.jyh.rest.presenter.impl;

import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.NewsDetailBean;
import com.jyh.rest.interactor.HoriztionPicBrowInteractor;
import com.jyh.rest.interactor.NewsDetailInteractor;
import com.jyh.rest.interactor.impl.HoriztionPicBrowInteractorImpl;
import com.jyh.rest.interactor.impl.NewsDetailInteractorImpl;
import com.jyh.rest.listener.HoriztionPicBrowCallBack;
import com.jyh.rest.listener.NewsDetailRequestCallBack;
import com.jyh.rest.presenter.HoriztonPicBrowPresenter;
import com.jyh.rest.presenter.NewsDetailPresenter;
import com.jyh.rest.view.HoriztonPicBrowlView;
import com.jyh.rest.view.NewsDetailView;

/**
 * Created by Administrator on 2016/10/29.
 */

public class HoriztionPicBrowPresenterImpl implements HoriztonPicBrowPresenter, HoriztionPicBrowCallBack {
    private HoriztonPicBrowlView view;
    private HoriztionPicBrowInteractor interactor;

    private NewsBean bean;

    public HoriztionPicBrowPresenterImpl(HoriztonPicBrowlView view, NewsBean bean) {
        this.view = view;
        this.bean = bean;
        interactor = new HoriztionPicBrowInteractorImpl(this);
    }

    @Override
    public void initialized() {
        view.showLoading();
        interactor.isCollectionChoice(bean.getDocid());
    }

    @Override
    public void collectionChoice() {
        view.showLoading();
        interactor.collectionChoice(bean);
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
