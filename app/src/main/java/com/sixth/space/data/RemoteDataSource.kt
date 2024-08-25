package com.sixth.space.data

import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.network.Resource


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
interface RemoteDataSource {
    suspend fun fetchHotList(str: String): Resource<List<VideoInfo>>

    suspend fun fetchReplyList(id: String): Resource<List<VideoInfo>>

    suspend fun fetchRecommend(id: String): Resource<List<VideoInfo>>

    suspend fun fetchHomeData(date: String, num: String): Resource<List<VideoInfo>>

}