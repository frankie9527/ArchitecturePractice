package com.jyh.rest.view;

import com.jyh.rest.base.BaseResponseView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public interface MainView<T> extends BaseResponseView{
    void onloadSuccess(List<T> data);
}
