package com.jyh.rest.presenter.impl;

import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.interactor.VideoListInteractor;
import com.jyh.rest.interactor.impl.VideoListInteractorImpl;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.presenter.VideoListPresenter;
import com.jyh.rest.view.ListNewsVideoView;


import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class VideoListPresenterImpl implements VideoListPresenter, RequestListCallBack<VideoBean> {
    private ListNewsVideoView view;
    /**
     * 当前加载的角标
     * 初始化是加载头
     * 角标为0
     * 每次下啦一下角标+1
     */
    private int current_position = 0;
    private int current_fragment;
    private VideoListInteractor interactor;
    private  Boolean isFrist=true;
    private  String type;

    public VideoListPresenterImpl(ListNewsVideoView view, int current_fragment) {
        this.view = view;
        this.current_fragment = current_fragment;
        interactor = new VideoListInteractorImpl(this);

    }

    @Override
    public void loadingHead() {
        if (isFrist){
            view.showLoading();
        }
       this.type=interactor.getTpye(current_fragment);
        interactor.loadVideoList(type,0);
        current_position=0;
    }

    @Override
    public void loadingFoot() {
        interactor.loadVideoList(type,current_position);
    }


    @Override
    public void onLoadHeadSuccess(List<VideoBean> data) {
        view.onloadHeadSuccess(data);
        current_position++;
        if (isFrist){
            isFrist=false;
        }
    }

    @Override
    public void onloadMoreSuccess(List<VideoBean> data) {
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
