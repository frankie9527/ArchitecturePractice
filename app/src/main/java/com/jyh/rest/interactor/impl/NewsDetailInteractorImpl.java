package com.jyh.rest.interactor.impl;

import android.util.Log;

import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.NewsDetailBean;
import com.jyh.rest.http.Network;
import com.jyh.rest.interactor.NewsDetailInteractor;
import com.jyh.rest.listener.NewsDetailRequestCallBack;
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
 * Created by Administrator on 2016/10/29.
 */

public class NewsDetailInteractorImpl implements NewsDetailInteractor {

    private NewsDetailRequestCallBack callBack;

    public NewsDetailInteractorImpl(NewsDetailRequestCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void loadNewsDetail(final String docid) {
        Network.getNews().getNewDetail(docid)
                .map(new Func1<Map<String, NewsDetailBean>, NewsDetailBean>() {
                    @Override
                    public NewsDetailBean call(Map<String, NewsDetailBean> map) {
                        NewsDetailBean newsDetail = map.get(docid);
                        changeNewsDetail(newsDetail);
                        return newsDetail;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onLoadNewsDetailError("加载失败");
                    }

                    @Override
                    public void onNext(NewsDetailBean newsDetail) {
                        callBack.onLoadNewsDetailSuccess(newsDetail);
                    }
                });
    }


    private void changeNewsDetail(NewsDetailBean newsDetail) {
        List<NewsDetailBean.NewsDetailImg> imgSrcs = newsDetail.getImg();
        if (isChange(imgSrcs)) {
            String newsBody = newsDetail.getBody();
            newsBody = changeNewsBody(imgSrcs, newsBody);
            newsDetail.setBody(newsBody);
        }
    }

    private boolean isChange(List<NewsDetailBean.NewsDetailImg> imgSrcs) {
        return imgSrcs != null && imgSrcs.size() >= 2;
    }



    private String changeNewsBody(List<NewsDetailBean.NewsDetailImg> imgSrcs, String newsBody) {
        for (int i = 0; i < imgSrcs.size(); i++) {
            String oldChars = "<!--IMG#" + i + "-->";
            String newChars;
            if (i == 0) {
                newChars = "";
            } else {
                //  newChars = "<img src=\"" + imgSrcs.get(i).getSrc() + "\" />";
                //  newChars="<img src=\"/i/mouse.jpg\" height=\"200\" width=\"200\" />";
                String pixel = imgSrcs.get(i).getPixel();
//                String width = pixel.substring(0, pixel.indexOf("*"));
//                String height = pixel.substring(pixel.indexOf("*")+1, pixel.length());
                String imgurl=imgSrcs.get(i).getSrc();
                //   newChars = "<img src=\"" + imgurl + "\"width=\""+width+"\" height=\""+height+"\" />";
                newChars = "<img src=\"" + imgurl + "\"width=\""+"100%\""+"\" height=\"auto\" />";
            }
            newsBody = newsBody.replace(oldChars, newChars);
        }
        Log.e("changeNewsBody",newsBody);

        return newsBody;
    }

    @Override
    public void collectionChoice(NewsBean bean) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        List<Collectionbean> list = dao.queryBuilder().where(CollectionbeanDao.Properties.DocId.eq(bean.getDocid())).build().list();
        if (list.size() > 0) {
            /**
             * 因为是收藏了的
             * 所以取消收藏
             * */
            dao.delete(list.get(0));
            callBack.onCollectionCancel();
        } else {
            Collectionbean collectionbean = JavaBeanTransUtils.newsNoraml2Bean(bean);
            collectionbean.setIsCollection(true);
            dao.insertOrReplace(collectionbean);
            callBack.onCollectionSuccess();
        }


    }

    @Override
    public void isCollectionChoice(String docid) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        List<Collectionbean> list = dao.queryBuilder().where(CollectionbeanDao.Properties.DocId.eq(docid), CollectionbeanDao.Properties.IsCollection.eq(true)).build().list();
        if (list.size() > 0) {
            callBack.onCollectionSuccess();
        } else {
            callBack.onCollectionCancel();
        }
    }
}
