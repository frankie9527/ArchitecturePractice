package com.jyh.sixthspace.viewmodel;

import androidx.databinding.BaseObservable;

import android.util.Log;
import android.view.View;

import com.jyh.sixthspace.sdk.base.Request2CallBack;
import com.jyh.sixthspace.sdk.bean.movie.VideoHttpResponse;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.bean.movie.VideoRes;
import com.jyh.sixthspace.sdk.http.NetWork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/7.
 */

public class MovieInfosViewModel extends BaseObservable {
    private VideoInfo info;
    private VideoRes res;
    private Request2CallBack<VideoRes, List<VideoInfo>> callBack;
    private Boolean isOpen=true;
    private String shortDes;
    private String longDes;
    public MovieInfosViewModel(VideoInfo info, Request2CallBack<VideoRes, List<VideoInfo>> callBack) {
        this.info = info;
        this.callBack = callBack;
        getDataFromNet();
    }
    public void getDataFromNet() {
        NetWork.getVideo().getVideoInfo(info.dataId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoHttpResponse<VideoRes>>() {
            @Override
            public void accept(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) throws Exception {
                VideoRes res = videoResVideoHttpResponse.getRet();
                dealData(res);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("error", throwable.toString());
            }
        });
    }

    public void dealData(VideoRes res) {
        this.res=res;
        List<VideoInfo> infos = res.list.get(0).childList;
        callBack.onRequestSuccess(res, infos);

        String dir = "导演：" + res.director;
        String act = "主演：" + res.actors;
        shortDes=dir + "\n" + act + "\n";
        longDes=shortDes+ "简介："+res.description;

        notifyChange();
    }

    public String getDescription() {
        if (res==null){
            return "";
        }else {
           if (isOpen){
               return shortDes;
           }else {
               return longDes;
           }
        }
    }

    public Boolean getIsOpen(){
        return isOpen;
    }

    public String getStringIsOpen(){
        if (isOpen){
            return "展开";
        }else {
            return "关闭";
        }
    }
    public void swithIsOpenStatu(View view){
        if (isOpen){
            isOpen=false;
        }else {
            isOpen=true;
        }
        notifyChange();
    }
}
