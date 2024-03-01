package com.sixth.space.network

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

}