package com.jyh.rest.utils;

import android.text.TextUtils;


import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/11/9.
 */

public class VideoUiUtils {
    public  static String getPlayCount(String count){
        if (TextUtils.isEmpty(count)){
            return 0+"次播放";
        }
        Long playcount=Long.parseLong(count);
        if (playcount<10000){
            return playcount+"次播放";
        }else {
            float num=playcount/10000;
            DecimalFormat df = new DecimalFormat("#.0");
            String str=df.format(num);
            return str+"万次播放";
        }

    }
}
