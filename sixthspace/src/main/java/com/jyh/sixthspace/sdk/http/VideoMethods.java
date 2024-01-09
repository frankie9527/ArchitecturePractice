
package com.jyh.sixthspace.sdk.http;






import com.jyh.sixthspace.sdk.bean.movie.VideoHttpResponse;
import com.jyh.sixthspace.sdk.bean.movie.VideoRes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface VideoMethods {
    String HOST = "http://api.svipmovie.com/front/";

    /**
     * 首页
     * http://api.svipmovie.com/front/homePageApi/homePage.do
     * @return
     */
    @POST("homePageApi/homePage.do")
    Observable<VideoHttpResponse<VideoRes>> getHomePage();

    /**
     * 影片详情
     *http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=603f402830354dac9be6c5269b2527eb
     * @param mediaId 影片id
     * @return
     */
    @GET("videoDetailApi/videoDetail.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoInfo(@Query("mediaId") String mediaId);

    /**
     * 影片分类列表
     *http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e538140009?pnum=1
     http://api.svipmovie.com/front/columns/getNewsList.do?catalogId=402834815584e463015584e539700019&information=0",
     * @param catalogId
     * @param pnum
     * @return
     */
    @GET("columns/getVideoList.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoList(@Query("catalogId") String catalogId, @Query("information") String pnum);

    /**
     * 影片搜索
     *
     * @param pnum
     * @return
     */
    @GET("searchKeyWordApi/getVideoListByKeyWord.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoListByKeyWord(@Query("keyword") String keyword, @Query("pnum") String pnum);

    /**
     * 获取评论列表
     * @param mediaId
     * @param pnum
     * @return
     */
    @GET("Commentary/getCommentList.do")
    Observable<VideoHttpResponse<VideoRes>> getCommentList(@Query("mediaId") String mediaId, @Query("pnum") String pnum);
}
