package com.jyh.sixthspace.viewmodel;


import android.text.TextUtils;
import android.util.Log;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.sdk.base.RequestCallBack;
import com.jyh.sixthspace.sdk.bean.movie.VideoHttpResponse;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.sdk.bean.movie.VideoRes;
import com.jyh.sixthspace.sdk.bean.movie.VideoType;
import com.jyh.sixthspace.sdk.http.NetWork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/9.
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539700019",
 * title":"热点资讯",
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e53843000b",
 * "title":"精彩推荐",
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e538140009",
 * "title":"好莱坞",
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e538ea0012",
 * "title":"微电影",
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=ff8080815b9075a6015b94ef79dc0284",
 * "title":"香港映象",
 * <p>
 * "moreURL":"http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e538d30011",
 * "title":"午夜剧场",
 */

public class MoviesFragmentViewModel {
    private RequestCallBack<List<VideoInfo>> callBack;
    private List<Integer> picList;

    public MoviesFragmentViewModel(RequestCallBack<List<VideoInfo>> callBack) {
        this.callBack = callBack;
        initData();
    }

    public void initData() {
        picList = new ArrayList<>();

        picList.add(R.drawable.movies_hot_infos);
        picList.add(R.drawable.moives_recmmend);
        picList.add(R.drawable.movies_big_shot);
        picList.add(R.drawable.movies_frist_look);
        picList.add(R.drawable.movies_micro_film);
        picList.add(R.drawable.movies_hongkong_film);
        picList.add(R.drawable.movies_hollywood);

        NetWork.getVideo().getHomePage().map(new Function<VideoHttpResponse<VideoRes>, List<VideoInfo>>() {
            @Override
            public List<VideoInfo> apply(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) throws Exception {
                List<VideoType> list = videoResVideoHttpResponse.getRet().list;
                return filterNoUseData(list);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<VideoInfo>>() {
            @Override
            public void accept(List<VideoInfo> list) throws Exception {
                callBack.onRequestSuccess(list);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("error", throwable.toString());
            }
        });
    }


    public List<VideoInfo> filterNoUseData(List<VideoType> list) {
        List<VideoInfo> filterList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String moreUrl = list.get(i).moreURL;
            if (!TextUtils.isEmpty(moreUrl)) {
                VideoInfo info = new VideoInfo();
                info.setTitle(list.get(i).title);
                info.setLocalPic(picList.get(filterList.size()));
                String catalogId = getCatalogId(moreUrl);
                info.setCatalogId(catalogId);
                filterList.add(info);
            }
        }
        return filterList;
    }

    /**
     * String str= "http://api.svipmovie.com/front/columns/getNewsList.do?catalogId=402834815584e463015584e539700019&information=0";
     * 只截取字符串中间的数字
     */
    public String getCatalogId(String str) {
        return str.split("catalogId=")[1].split("\\&")[0];
    }

    ;
}
