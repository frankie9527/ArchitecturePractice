package com.jyh.rest.listener;

import com.jyh.rest.entity.NewsDetailBean;

/**
 * Created by Administrator on 2016/10/28.
 *
 * 网络请求的回调
 */

public interface NewsDetailRequestCallBack {
    void onLoadNewsDetailSuccess(NewsDetailBean bean);
    void onLoadNewsDetailError(String error);
    void onCollectionSuccess();
    void onCollectionCancel();
}
