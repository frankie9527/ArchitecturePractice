package com.jyh.sixthspace.live.utils;


import com.jyh.sixthspace.sdk.utlis.MD5Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ParamsMapUtils extends BaseParamsMapUtil {

    private static Map<String, String> mapparam;

    /**
     * 默认参数
     *
     * @return
     */
    public static Map<String, String> getDefaultParams() {
        return getParamsMap();
    }

    /**
     *   首页 列表详情
     * @param identification
     * @return
     */
    public static Map<String, String> getHomeCate(String identification) {
        mapparam = getDefaultParams();
        mapparam.put("identification", identification);
        return mapparam;
    }

    public static Map<String, String> getHomeCarousel() {
        mapparam = getDefaultParams();
        mapparam.put("version", "2.421");
        return mapparam;
    }
    /**
     *    首页--推荐--颜值
     *      默认  :4条数据
     * @return
     */
    public static Map<String, String> getHomeFaceScoreColumn(int offset,int limit) {
        mapparam = getDefaultParams();
        mapparam.put("offset",offset+"");
        mapparam.put("limit", limit+"");
        return mapparam;
    }

    /**
     *    其他栏目二级分类
     * @param ColumnName
     * @return
     */
    public static Map<String, String> getLiveOtherTwoColumn(String ColumnName) {
        mapparam = getDefaultParams();
        mapparam.put("shortName",ColumnName);
        return mapparam;
    }

    /**
     *   栏目 更多 二级分类列表
     * @param cate_id
     * @return
     */
    public static Map<String, String> getColumnMoreCate(String cate_id) {
        mapparam = getDefaultParams();
        mapparam.put("tag_id",cate_id);
        return mapparam;
    }

    /**
     *    房间信息
     * @param room_id
     * @return
     */
    public static Map<String, String> getLiveVideoInfo(String  room_id) {
        mapparam =new HashMap<String,String>();
        long time = System.currentTimeMillis()/(1000*60);
        String did = UUID.randomUUID().toString().toUpperCase();
        did = did.replace("-", "");
        String str = room_id + did + "A12Svb&%1UUmf@hC" + time;
        String sign = MD5Util.getToMd5Low32(str);
        mapparam.put("client_sys", "android");
        mapparam.put("aid", "android1");
        mapparam.put("cdn", "ws");
        mapparam.put("rate", "0");
        mapparam.put("tt", String.valueOf(time));
        mapparam.put("did", did);
        mapparam.put("sign", sign);
        return mapparam;
    }

    /**
     *    首页--推荐--颜值
     *      默认  :4条数据
     * @return
     */
    public static Map<String, String> getHomeColumnMoreOtherList(String cate_id,int offset,int limit) {
        mapparam = getDefaultParams();
        mapparam.put("cate_id",cate_id);
        mapparam.put("offset",offset+"");
        mapparam.put("limit", limit+"");
        return mapparam;
    }

/**
 * *******************************视频模块****************************************
 */

    public static Map<String, String> getVideoOtherTwoColumn(String ColumnName) {
        mapparam = getDefaultParams();
        mapparam.put("cid1",ColumnName);
        return mapparam;
    }

//    cid1=1&cid2=5&offset=0&limit=20&action=hot

    public static Map<String, String> getVideoOtherList(String cid1,String cid2 ,int offset,int limit,String action) {
        mapparam = getDefaultParams();
        mapparam.put("cid1",cid1);
        mapparam.put("cid2",cid2);
        mapparam.put("offset",offset+"");
        mapparam.put("limit",limit+"");
        mapparam.put("action",action);
        return mapparam;
    }

    public static Map<String,String> getPersonInfo(String userName,String password) {
        mapparam=getDefaultParams();
        mapparam.put("username",userName);
        mapparam.put("password",password);
        return  mapparam;
    }
}
