package com.jyh.rest.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.jyh.rest.base.Constant;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.interactor.CollectionOrSearchInteractor;
import com.jyh.rest.interactor.impl.CollectionOrSearchInteractorImpl;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.presenter.CollectionOrSearchPresenter;
import com.jyh.rest.ui.activity.HoriztonPicBrowActivity;
import com.jyh.rest.ui.activity.NewsDetailActivity;
import com.jyh.rest.ui.activity.VideoPlayActivity;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.JsonUtils;
import com.jyh.rest.view.ListNewsVideoView;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 * <p>
 * 收藏不需要下拉刷新。所以直接loadmore
 */

public class CollectionOrSearchPresenterImpl implements CollectionOrSearchPresenter, RequestListCallBack<Collectionbean> {
    private int load_position = 0;
    ListNewsVideoView view;
    CollectionOrSearchInteractor interator;

    public CollectionOrSearchPresenterImpl(ListNewsVideoView view) {
        this.view = view;
        interator = new CollectionOrSearchInteractorImpl(this);
    }

    @Override
    public void load() {
        view.showLoading();
        interator.load(load_position);
    }


    @Override
    public void go2Activity(Collectionbean bean, Activity activity) {
        Intent intent = null;
        Bundle bundle = new Bundle();
        int type = bean.getType();
        switch (type) {
            case Constant.NEWS_NORAML:
                intent = new Intent(activity, NewsDetailActivity.class);
                NewsBean newsBean = JavaBeanTransUtils.Collection2NewNormal(bean);
                bundle.putSerializable("bean", newsBean);
                break;
            case Constant.NEWS_IMGEXTRA:
                intent = new Intent(activity, HoriztonPicBrowActivity.class);
                NewsBean newsBeanimg = null;
                try {
                    newsBeanimg = JsonUtils.String2NewsBean(bean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                bundle.putSerializable("bean", newsBeanimg);
                break;
            case Constant.NEWS_VIDEO:
                intent = new Intent(activity, VideoPlayActivity.class);
                VideoBean videoBean = JavaBeanTransUtils.Collection2Video(bean);
                bundle.putSerializable("bean", videoBean);
                break;
        }
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public void search(String keyWord) {
        if (TextUtils.isEmpty(keyWord)) {
            view.showError("请输入内容(づ￣3￣)づ╭❤～");
            return;
        }
        view.showLoading();
        interator.search(keyWord);
    }

    @Override
    public void onLoadHeadSuccess(List<Collectionbean> data) {

    }

    @Override
    public void onloadMoreSuccess(List<Collectionbean> data) {
        view.onloadMoreSuccess(data);
        load_position++;
        view.hideLoading();
    }

    @Override
    public void onloadHeadError(String error) {
        view.hideLoading();
    }

    @Override
    public void onloadMoreError(String error) {
        view.hideLoading();
    }
}
