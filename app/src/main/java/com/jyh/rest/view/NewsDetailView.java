package com.jyh.rest.view;

import com.jyh.rest.base.BaseResponseView;
import com.jyh.rest.entity.NewsDetailBean;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface NewsDetailView extends BaseResponseView {
    void onLoadNewsDetailSuccess(NewsDetailBean bean);
    void onCollectionNews();
    void onCancelCollectionNews();
}
