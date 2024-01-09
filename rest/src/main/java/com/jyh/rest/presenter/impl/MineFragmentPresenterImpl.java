package com.jyh.rest.presenter.impl;

import android.app.Activity;
import android.content.Intent;

import com.jyh.rest.entity.MyinfosBean;
import com.jyh.rest.interactor.MineFragmentInteractor;
import com.jyh.rest.interactor.impl.MineFragmentInteractorImpl;
import com.jyh.rest.listener.MyInfosListener;
import com.jyh.rest.presenter.MineFragmentPresenter;
import com.jyh.rest.ui.activity.LoginActivity;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.view.MineFragmentView;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2016/11/20.
 */

public class MineFragmentPresenterImpl implements MineFragmentPresenter,MyInfosListener {
    MineFragmentView view;
    MineFragmentInteractor interactor;
    public MineFragmentPresenterImpl(MineFragmentView view,Activity activity) {
        this.view=view;
        this.interactor=new MineFragmentInteractorImpl(this,activity);

    }


    @Override
    public void initialized() {

        interactor.getUserInfos();
    }

    @Override
    public void login(Activity activity) {
        Boolean isLogin=interactor.isLogin();
        /**
         * 如果是登录状态，则不登录
         * */
        if (isLogin)return;
        Intent intent = new Intent();
        intent.setClass(activity, LoginActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void loadMyinfosError() {
        ToastUtils.getInstance().show("加载失败了");
    }

    @Override
    public void loadMyinfosSuccess(MyinfosBean bean) {
         view.loadMyinfosSuccess(bean);
    }
}
