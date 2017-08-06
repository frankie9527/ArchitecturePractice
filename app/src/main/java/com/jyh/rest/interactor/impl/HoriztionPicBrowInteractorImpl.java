package com.jyh.rest.interactor.impl;

import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.interactor.HoriztionPicBrowInteractor;
import com.jyh.rest.listener.HoriztionPicBrowCallBack;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.GreenDaoMananger;
import com.jyh.rest.utils.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */

public class HoriztionPicBrowInteractorImpl implements HoriztionPicBrowInteractor {
    HoriztionPicBrowCallBack callBack;

    public HoriztionPicBrowInteractorImpl(HoriztionPicBrowCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void collectionChoice(NewsBean bean) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        List<Collectionbean> list = dao.queryBuilder().where(CollectionbeanDao.Properties.DocId.eq(bean.getDocid()), CollectionbeanDao.Properties.IsCollection.eq(true)).build().list();
        if (list.size() > 0) {
            /**
             * 因为是收藏了的
             * 所以取消收藏
             * */
            dao.delete(list.get(0));
            callBack.onCollectionCancel();
        } else {
            Collectionbean collectionbean = JavaBeanTransUtils.newsImgextra2Bean(bean);
            collectionbean.setIsCollection(true);
            dao.insertOrReplace(collectionbean);
            callBack.onCollectionSuccess();
        }
    }

    @Override
    public void isCollectionChoice(String docid) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        List<Collectionbean> list = dao.queryBuilder().where(CollectionbeanDao.Properties.DocId.eq(docid)).build().list();
        if (list.size() > 0) {
            callBack.onCollectionSuccess();
        } else {
            callBack.onCollectionCancel();
        }
    }
}
