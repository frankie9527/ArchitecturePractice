package com.jyh.rest;

import android.app.Application;
import android.content.Context;
import android.util.SparseArray;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by jyh on 2016/9/7.
 */
public class RestApplication extends Application {
    /**
     * 上下文对象
     * */
    private static Context mContext;

    private static SparseArray<String> newsPosition2type;
    private static SparseArray<String> videoPosition2type;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        /**   初始化日志采集器*/
      //  CrashHandler.getInstance().init(mContext);
        /**网络日志采集*/
       CrashReport.initCrashReport(getApplicationContext(), "b878e1ce18", false);
        initNews();
        initVideos();

        UMShareAPI.get(this);
        PlatformConfig.setQQZone("1105718006", "mpmBCgiScYlUhm7z");
        PlatformConfig.setWeixin("wx2221defdd2126be4","a1a618f9acf3366dac32c7efae00479a");
    }



    private void initNews() {
        newsPosition2type =new SparseArray<>();

        newsPosition2type.put(0,"T1348647909107");
        newsPosition2type.put(1,"T1348648037603");
        newsPosition2type.put(2,"T1370583240249");
        newsPosition2type.put(3,"T1348650839000");
        newsPosition2type.put(4,"T1348649580692");
    }
    private void initVideos() {
        videoPosition2type=new SparseArray<>();
        /**
         *热点视频
         * */
        videoPosition2type.put(0,"V9LG4B3A0");
        /**
         *娱乐视频
         * */
        videoPosition2type.put(1,"V9LG4CHOR");
        /**
         *搞笑视频
         * */
        videoPosition2type.put(2,"V9LG4E6VR");
        /**
         * 精品视频
         * */
        videoPosition2type.put(3,"00850FRB");

    }
    /**
     * 全局上下文
     */
    public static Context getContext() {
        return mContext;
    }

    public  static SparseArray<String> getNewsPosition2type(){
        return newsPosition2type;
    }

    public  static SparseArray<String> getVideoPosition2type(){
        return videoPosition2type;
    }


}
