package com.jyh.sixthspace.sdk.constant;

/**
 * 直播的url
 **/
public class LiveConStant {

    //    Base地址
    public static String baseUrl = "http://capi.douyucdn.cn";

    //     OldBase地址
    public static String oldBaseUrl = "http://coapi.douyucdn.cn";

    //     视频接口 地址
    public static String baseVideoUrl = "https://apiv2.douyucdn.cn";


    /**
     * ************************* 首页接口*******************************
     */
    //****************************推荐模块***************************************

    //    首页轮播
    public static final String getCarousel = "/api/v1/slide/6";
    //    首页---推荐---热栏目
    public static final String getHomeHotColumn = "/api/v1/getbigDataRoom";
    //    首页---颜值栏目
    public static final String getHomeFaceScoreColumn = "/api/v1/getVerticalRoom";
    //    其他热门 种类
    public static final String getHomeRecommendHotCate = "/api/v1/getHotCate";
    //    栏目更多 --- 二级分类列表
    public static final String getHomeColumnMoreCate = "/api/v1/getThreeCate";
    //    栏目更多 --- 全部列表
    public static final String getHomeColumnMoreAllList = "/api/v1/live/";
    //    栏目更多----其他列表
    public static final String getHomeColumnMoreOtherList = "/api/v1/getThreeList";

    //****************************其他***************************************
//     首页列表
    public static final String getHomeCateList = "/api/homeCate/getCateList";
    //     列表详情
    public static final String getHomeCate = "/api/homeCate/getHotRoom";


    /**
     *   ********************************************************************
     */
    /**
     * ************************* 直播接口*******************************
     */
//    直播其他栏目分类
    public static final String getLiveOtherColumn = "/api/v1/getColumnList";
    //    全部直播
    public static final String getLiveAllList = "/api/v1/live";
    //    其他二级栏目分类
    public static final String getLiveOtherTwoColumn = "/api/v1/getColumnDetail";
    //   二级栏目分类列表
    public static final String getLiveOtherTwoList = "/api/v1/live/";
    //    体育直播
    public static final String getLiveSportsAllList = "/api/v1/qie";

    /**
     * *****************************视频接口***************************************
     */
    //    视频---推荐  http://apiv2.douyucdn.cn/video/Video/getHotVideoList1?clicknum=2&token=&client_sys=android
    public static final String getVideoHotColumn = "/video/Video/getHotVideoList1";

    //    视频---热门作者栏目  http://apiv2.douyucdn.cn/video/Home/getHotAuthors?client_sys=android
    public static final String getVideoHotAutherColumn = "/video/Home/getHotAuthors";

    //    其他热门 种类  http://apiv2.douyucdn.cn/video/Video/getCateHotVideoList1?token=&client_sys=android
    public static final String getVideoRecommendHotCate = "/video/Video/getCateHotVideoList1";

    //  视频---全部分类(一级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoHomeCate1?client_sys=android
    public static final String getVideoCateList = "/video/Cate/getVideoHomeCate1";

    //  视频---全部分类(二级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoCate2?cid1=3&client_sys=android
    public static final String getVideoReCateList = "/video/Cate/getVideoCate2";
    // 视频---视频列表 http://apiv2.douyucdn.cn/video/Videoroomlist/getRecVideoList?cid1=1&cid2=5&offset=0&limit=20&action=hot&client_sys=android
    public static final String getVideoOtherList = "/video/Videoroomlist/getRecVideoList";

    /**
     * ************************* 直播视频 *******************************
     */
//    新接口
    public static final String getLiveVideo = "/api/v1/room/";

    //    老接口
    public static final String getOldLiveVideo = "/lapi/live/thirdPart/getPlay/";
    /**
     * ********************************************************************
     */


    public static final String getPersonInfo = "/api/v1/login";

    //     视频 流地址
    public static final String getVideoPlay = "/videonc/Stream/getAppPlayer";


}
