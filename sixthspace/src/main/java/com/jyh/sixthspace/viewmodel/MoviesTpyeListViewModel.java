package com.jyh.sixthspace.viewmodel;


import com.jyh.sixthspace.sdk.bean.movie.VideoHttpResponse;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.bean.movie.VideoRes;
import com.jyh.sixthspace.sdk.bean.movie.VideoType;
import com.jyh.sixthspace.sdk.http.NetWork;

import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/5/15.
 */

public class MoviesTpyeListViewModel extends Observable {
    private VideoInfo info;
    private int pageCount = 20;

    public MoviesTpyeListViewModel(VideoInfo info) {
        this.info = info;
        getData();
    }

    public void getData() {
        NetWork.getVideo().getVideoList(info.getCatalogId(), pageCount + "").map(new Function<VideoHttpResponse<VideoRes>, List<VideoType>>() {
            @Override
            public List<VideoType> apply(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) throws Exception {
                return videoResVideoHttpResponse.getRet().list;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<VideoType>>() {
            @Override
            public void accept(List<VideoType> videoTypes) throws Exception {
               dealData(videoTypes);
                pageCount++;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void dealData(List<VideoType> videoInfos) {
        setChanged();
        notifyObservers(videoInfos);
    }
}
