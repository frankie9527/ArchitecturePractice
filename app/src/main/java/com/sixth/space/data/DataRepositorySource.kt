package com.sixth.space.data

import com.sixth.space.base.BaseResp
import com.sixth.space.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
interface DataRepositorySource {
    suspend fun getHotList(str: String): Flow<Resource<BaseResp<HotItem>>>
    suspend fun fetchReplyComment(id:String): Flow<Resource<BaseResp<ReplyItem>>>
}