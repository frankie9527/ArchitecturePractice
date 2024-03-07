package com.sixth.space.data

import com.sixth.space.base.HttpResponse
import com.sixth.space.network.Resource


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
interface RemoteDataSource {
    suspend fun fetchHotList(str: String): Resource<HttpResponse<HotItem>>

    suspend fun fetchReplyComment(id: String): Resource<HttpResponse<CommentItem>>

    suspend fun fetchRecommend(id: String): Resource<HttpResponse<RecommendItem>>
}