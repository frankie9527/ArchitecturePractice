package com.jyh.sixthspace.sdk.http;




import com.jyh.sixthspace.sdk.constant.LiveConStant;
import com.jyh.sixthspace.sdk.utlis.UIUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/17.
 */

public class NetWork {
    private static OkHttpClient mOkHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static VideoMethods videoMethods;
    private static void initOkhttp() {
        if (mOkHttpClient == null) {
            synchronized (NetWork.class) {
                if (mOkHttpClient == null) {
                    File mFile = new File(UIUtils.getContext().getCacheDir(), "cache");
                    Cache mCache = new Cache(mFile, 1024 * 1024 * 200);
                   NetworkInterceptor mNetworkInterceptor = new NetworkInterceptor();
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(mCache)
                            .addInterceptor(mNetworkInterceptor)
                            .addInterceptor(new LogInterceptor())
                            .addNetworkInterceptor(mNetworkInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
    public  static VideoMethods getVideo(){
        initOkhttp();
        if (videoMethods == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(VideoMethods.HOST)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            videoMethods = retrofit.create(VideoMethods.class);
        }
        return videoMethods;
    }

    public static <T> T  LiveBuilder(Class<T> service)
    {
        initOkhttp();
        Retrofit  retrofit=new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(LiveConStant.baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        return retrofit.create(service);
    }
}
