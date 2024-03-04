package com.sixth.space.network

import com.sixth.space.data.HotList
import com.sixth.space.data.ReplyList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 */
interface RetrofitService {
    @GET("api/v4/rankList/videos")
    suspend fun getHotList(
        @Query("strategy") strategy: String
    ): HotList

    @GET("/api/v2/replies/video")
    suspend fun getReply(
        @Query("lastId") lastId: String,
        @Query("videoId") videoId: String,
        @Query("num") num: String,
        @Query("type") type: String
    ): ReplyList
}