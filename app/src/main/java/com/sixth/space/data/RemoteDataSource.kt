package com.sixth.space.data

import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.network.Resource
import kotlinx.coroutines.flow.Flow


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
interface RemoteDataSource {
    suspend fun fetchHotList(str: String): Resource<List<VideoInfo>>

    suspend fun fetchReplyComment(id: String): Resource<List<VideoInfo>>

    suspend fun fetchRecommend(id: String): Resource<List<VideoInfo>>

    suspend fun fetchTiktokData(date: String, num: String): Resource<List<VideoInfo>>

}