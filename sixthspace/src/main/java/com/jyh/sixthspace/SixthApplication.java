package com.jyh.sixthspace;

import android.app.Application;
import android.content.Context;

import com.jyh.sixthspace.sdk.SixthSpaceSdk;


/**
 * Created by Administrator on 2017/4/8.
 */

public class SixthApplication  extends Application{
    /**
     *
     *
     * */
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SixthSpaceSdk.init(mContext);
    }
    /**
     * 全局上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
