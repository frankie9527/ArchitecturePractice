package com.jyh.sixthspace.sdk.http;




import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.bean.live.HomeCateList;
import com.jyh.sixthspace.sdk.bean.live.HomeFaceScoreColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;
import com.jyh.sixthspace.sdk.bean.live.HttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;




/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/12 下午4:12
 **/
public interface LiveHomeMethods {

    /**
     * 首页分类列表
     *
     * @return
     */
    @GET("/api/homeCate/getCateList")
    Observable<HttpResponse<List<HomeCateList>>> getHomeCateList(@QueryMap Map<String, String> params);

    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET("/api/v1/slide/6")
    Observable<HttpResponse<List<HomeCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET("/api/v1/getbigDataRoom")
    Observable<HttpResponse<List<HomeHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---美女
     *
     * @return
     */
    @GET("/api/v1/getVerticalRoom")
    Observable<HttpResponse<List<HomeFaceScoreColumn>>> getBeautys(@QueryMap Map<String, String> params);

    /**
     * 推荐---热门 其他种类
     *
     * @return
     */
    @GET("/api/v1/getHotCate")
    Observable<HttpResponse<List<HomeRecommendHotCate>>> getHotOther(@QueryMap Map<String, String> params);
}
