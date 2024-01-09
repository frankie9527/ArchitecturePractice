package com.jyh.rest.presenter;

import android.app.Activity;

import com.jyh.rest.entity.Collectionbean;

import org.json.JSONException;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface CollectionOrSearchPresenter {
    void load();
    void go2Activity(Collectionbean bean, Activity activity) ;
    void search(String keyWord);
}
