package com.jyh.rest.interactor.impl;

import com.jyh.rest.RestApplication;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.http.Network;
import com.jyh.rest.interactor.VideoListInteractor;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.utils.CrashHandler;
import com.jyh.rest.utils.GreenDaoMananger;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.UIUtils;


import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class VideoListInteractorImpl implements VideoListInteractor {
    private RequestListCallBack<VideoBean> callBack;

    public VideoListInteractorImpl(RequestListCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void loadVideoList(final String type, final int position) {
        final String where = position * 20 + "";
        Network.getVideo().getVideoList(type, where).map(new Func1<Map<String, List<VideoBean>>, List<VideoBean>>() {
            @Override
            public List<VideoBean> call(Map<String, List<VideoBean>> map) {
                List<VideoBean> list = map.get(type);
                return list;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<VideoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (position==0){
                            callBack.onloadHeadError("刷新失败");
                        }else{
                            callBack.onloadHeadError("加载更多失败");
                        }

                    }

                    @Override
                    public void onNext(List<VideoBean> list) {
                        if (position==0){
                            callBack.onLoadHeadSuccess(list);
                        }else{
                            callBack.onloadMoreSuccess(list);
                        }
                        BatchData(list);
                    }
                });
    }

    @Override
    public String getTpye(int current_fragment) {
        return RestApplication.getVideoPosition2type().get(current_fragment);
    }
    public void BatchData(List<VideoBean> list) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        Collectionbean collectionBean = null;
        for (int i = 0; i < list.size(); i++) {
            collectionBean = JavaBeanTransUtils.Video2Bean(list.get(i));
            dao.insertOrReplace(collectionBean);
        }
    }
}
