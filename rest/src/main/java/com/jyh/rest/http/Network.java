package com.jyh.rest.http;

import com.jyh.rest.utils.UIUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class Network {
    private static OkHttpClient mOkHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static NewsMethods newsMethods;
    private static VideoMethods videoMethods;

    private static void initOkhttp() {
        if (mOkHttpClient == null) {
            synchronized (Network.class) {
                if (mOkHttpClient == null) {
                    File mFile = new File(UIUtils.getContext().getCacheDir(), "cache");
                    Cache mCache = new Cache(mFile, 1024 * 1024 * 200);
                    NetworkInterceptor mNetworkInterceptor = new NetworkInterceptor();

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(mCache)
                            .addInterceptor(mNetworkInterceptor)
                            .addNetworkInterceptor(mNetworkInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public static NewsMethods getNews() {
        initOkhttp();
        if (newsMethods == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(GlobalUrl.NEWS_BASE)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)

                    .build();
            newsMethods = retrofit.create(NewsMethods.class);
        }
        return newsMethods;
    }

    public  static VideoMethods getVideo(){
        initOkhttp();
        if (videoMethods == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(GlobalUrl.VIDEO_BASE)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            videoMethods = retrofit.create(VideoMethods.class);
        }
        return videoMethods;
    }
}
