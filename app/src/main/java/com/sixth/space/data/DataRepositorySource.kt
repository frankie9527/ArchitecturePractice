package com.sixth.space.data

import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
interface DataRepositorySource {
    suspend fun fetchHotList(str: String): Flow<Resource<List<VideoInfo>>>
    suspend fun fetchReplyComment(id: String): Flow<Resource<List<VideoInfo>>>
    suspend fun fetchRecommend(id: String): Flow<Resource<List<VideoInfo>>>
    suspend fun fetchTiktokData(date: String, num: String): Flow<Resource<List<VideoInfo>>>
}