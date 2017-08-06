package com.jyh.rest.listener;

import com.jyh.rest.entity.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 *
 * 网络请求的回调
 */

public interface RequestListCallBack<T> {
    void onLoadHeadSuccess(List<T> data);
    void onloadMoreSuccess(List<T> data);

    void onloadHeadError(String error);
    void onloadMoreError(String error);
}
