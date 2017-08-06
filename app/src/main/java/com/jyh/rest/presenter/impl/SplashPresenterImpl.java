package com.jyh.rest.presenter.impl;

import com.jyh.rest.interactor.SplashInteractor;
import com.jyh.rest.interactor.impl.SplashInteractorImpl;
import com.jyh.rest.listener.OnFinishedListener;
import com.jyh.rest.presenter.SplashPresenter;
import com.jyh.rest.view.SplashView;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class SplashPresenterImpl implements SplashPresenter ,OnFinishedListener {
    SplashView view;
    SplashInteractor interactor;

    public SplashPresenterImpl(SplashView view) {
        this.view = view;
        interactor = new SplashInteractorImpl();
    }

    @Override
    public void initialized() {
        view.showCopyRight(interactor.getCopyright());
        view.showSlogan(interactor.getslogan());
        view.showSplashImg(interactor.getSpalshImg());
        interactor.countSplashTime(this);
    }

    @Override
    public void onFinish() {
        view.go2Main();
    }
}
