package com.jyh.sixthspace.live.utils;

import java.util.LinkedHashMap;
import java.util.Map;


public class BaseParamsMapUtil {

    /**
     * 公共的参数集合
     *
     * @return
     */
    public static Map<String, String> getParamsMap() {
        Map<String, String> paramsmap = new LinkedHashMap<>();
        paramsmap.put("client_sys", "android");
        paramsmap.put("aid", "android1");
        paramsmap.put("time",System.currentTimeMillis()+"");
        return paramsmap;
    }


}
