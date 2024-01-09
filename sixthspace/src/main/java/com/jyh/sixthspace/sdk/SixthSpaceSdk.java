package com.jyh.sixthspace.sdk;

import android.content.Context;


import com.jyh.sixthspace.sdk.utlis.CrashHandler;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/9/26.
 */

public class SixthSpaceSdk {
    /**
     * 上下文对象
     * */
    private static Context mContext;
    public static void init(Context initContext){
        mContext=initContext;
        CrashHandler.getInstance().init(mContext);
        /** 检测ui卡顿*/
//        BlockCanary.install(initContext, new AppBlockCanaryContext()).start();
        CrashReport.initCrashReport(mContext, "d89e25f3d1", false);
    }
    /**
     * 全局上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
