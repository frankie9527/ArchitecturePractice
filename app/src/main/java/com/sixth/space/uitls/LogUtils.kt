package com.sixth.space.uitls

import android.util.Log
import com.sixth.space.BuildConfig

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class LogUtils {
    companion object{
        /**
         * 打印普通日志
         */
        fun d(tag: String, str: String?) {
            if (BuildConfig.DEBUG) {
                Log.d("LogUtils_" + tag, str!!)
            }
        }

        /**
         * 打印错误日志
         */
        fun e(tag: String, str: String?) {
            if (BuildConfig.DEBUG) {
                Log.e("LogUtils_"  + tag, str!!)
            }
        }
    }


}