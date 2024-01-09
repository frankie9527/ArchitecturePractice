package com.jyh.rest.interactor.impl;

import android.app.Activity;

import com.jyh.rest.base.Constant;
import com.jyh.rest.entity.MyinfosBean;
import com.jyh.rest.http.GlobalUrl;
import com.jyh.rest.interactor.MineFragmentInteractor;
import com.jyh.rest.listener.MyInfosListener;
import com.jyh.rest.utils.CrashHandler;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.utils.spUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/20.
 */

public class MineFragmentInteractorImpl implements MineFragmentInteractor {
    MyInfosListener listener;
    private Activity activity;
    public MineFragmentInteractorImpl(MyInfosListener listener,Activity activity) {
        this.listener=listener;
        this.activity=activity;
    }



    @Override
    public void getUserInfos() {
        MyinfosBean bean=new MyinfosBean();
        String headPath= (String) spUtils.get(Constant.HEAD_PATH,"");
        String name= (String) spUtils.get(Constant.NAME,"");
        bean.setImg_url(headPath);
        bean.setName(name);
        listener.loadMyinfosSuccess(bean);
    }

    @Override
    public Boolean isLogin() {
        return (Boolean) spUtils.get(Constant.IS_LOGIN,false);
    }

}
