package com.sixth.space.data

import com.sixth.space.base.HttpResponse
import com.sixth.space.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
interface DataRepositorySource {
    suspend fun fetchHotList(str: String): Flow<Resource<HttpResponse<HotItem>>>
    suspend fun fetchReplyComment(id:String): Flow<Resource<HttpResponse<ReplyItem>>>

    suspend fun fetchRecommend(id:String): Flow<Resource<HttpResponse<RecommendItem>>>
}