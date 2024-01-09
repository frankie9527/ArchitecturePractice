package com.jyh.rest.view;

import com.jyh.rest.base.BaseResponseView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 *
 * 新闻 ，视频， 收藏的view
 */
public interface ListNewsVideoView<T> extends BaseResponseView {
    /**
     * 加载头部成功
     * */
    void onloadHeadSuccess(List<T> data);
    /**
     * 加载头部数据失败
     * */
    void onloadHeadError(String error);
    /**
     * 加载更多成功
     * */
    void onloadMoreSuccess(List<T> data);
}
