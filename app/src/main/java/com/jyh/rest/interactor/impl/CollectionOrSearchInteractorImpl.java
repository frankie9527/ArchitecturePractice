package com.jyh.rest.interactor.impl;

import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.interactor.CollectionOrSearchInteractor;
import com.jyh.rest.listener.RequestListCallBack;
import com.jyh.rest.utils.GreenDaoMananger;
import com.jyh.rest.utils.UIUtils;


import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class CollectionOrSearchInteractorImpl implements CollectionOrSearchInteractor {
    RequestListCallBack<Collectionbean> callBack;
    CollectionbeanDao dao= (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
    public CollectionOrSearchInteractorImpl(RequestListCallBack<Collectionbean> callBack) {
        this.callBack = callBack;
    }
    @Override
    public void load(int postion) {
        List<Collectionbean> list=  dao.queryBuilder().where(CollectionbeanDao.Properties.IsCollection.eq(true)).build().list();
        callBack.onloadMoreSuccess(list);
    }

    @Override
    public void search(String keyWord) {
        List<Collectionbean> list=  dao.queryBuilder().where(CollectionbeanDao.Properties.Title.like("%"+keyWord+"%")).build().list();
        callBack.onloadMoreSuccess(list);
    }
}
