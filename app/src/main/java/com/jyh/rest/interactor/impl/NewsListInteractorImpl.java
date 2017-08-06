package com.jyh.rest.interactor.impl;

import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.http.Network;
import com.jyh.rest.interactor.NewsListInteractor;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.GreenDaoMananger;
import com.jyh.rest.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class NewsListInteractorImpl implements NewsListInteractor {
    private RequestListCallBack<NewsBean> callBack;

    public NewsListInteractorImpl(RequestListCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void loadNewsList(final String type, final String id, final int position) {
        final String where = position * 20 + "";
        Network.getNews().getHeadlinesList(type, id, where).map(new Func1<Map<String, List<NewsBean>>, List<NewsBean>>() {
            @Override
            public List<NewsBean> call(Map<String, List<NewsBean>> map) {
                List<NewsBean> list = map.get(id);
                List<NewsBean> newList = new ArrayList<NewsBean>();
                if (list != null) {
                    for (NewsBean bean : list) {
                        /**
                         * 如果不是多图的布局
                         * */
                        if (bean.getImgextra() == null) {
                            newList.add(bean);
                        } else {
                            /**
                             * 如果是多图的布局，那么把一般的
                             * image 地址加进多图的集合里面
                             * */
                            NewsBean new_bean = bean;
                            NewsBean.Imgextra extra = new NewsBean.Imgextra();
                            extra.setImgsrc(bean.getImgsrc());
                            new_bean.getImgextra().add(extra);

                            newList.add(new_bean);
                        }

                    }
                }
                return newList;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (position == 0) {
                            callBack.onloadHeadError("刷新失败");
                        } else {
                            callBack.onloadHeadError("加载更多失败");
                        }

                    }

                    @Override
                    public void onNext(List<NewsBean> list) {
                        if (position == 0) {
                            callBack.onLoadHeadSuccess(list);
                        } else {
                            callBack.onloadMoreSuccess(list);
                        }
                        BatchData(list);
                    }
                });
    }

    public void BatchData(List<NewsBean> list) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        Collectionbean collectionBean = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getImgextra() == null) {
                collectionBean = JavaBeanTransUtils.newsNoraml2Bean(list.get(i));
                dao.insertOrReplace(collectionBean);
            } else {

            }
        }
    }
}
