package com.jyh.rest.listener;

import com.jyh.rest.entity.MyinfosBean;

/**
 * Created by Administrator on 2016/11/20.
 */

public interface MyInfosListener {
    void loadMyinfosError();
    void loadMyinfosSuccess(MyinfosBean bean);
}
