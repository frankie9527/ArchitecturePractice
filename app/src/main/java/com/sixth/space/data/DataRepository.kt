package com.sixth.space.data


import com.sixth.space.network.Resource
import com.sixth.space.network.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {
    override suspend fun getHotList(str: String): Flow<Resource<HotList>> {
        return flow {
            emit(remoteRepository.getHotList(str))
        }.flowOn(ioDispatcher);
    }

    override suspend fun fetchReplyComment(id: String): Flow<Resource<ReplyList>> {
        return flow {
            emit(remoteRepository.fetchReplyComment(id))
        }.flowOn(ioDispatcher);
    }

}