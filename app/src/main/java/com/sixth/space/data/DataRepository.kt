package com.sixth.space.data


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
    private val service: RetrofitService,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {
    override suspend fun getHotList(position: Int): Flow<Resource<HotList>> {
        return flow {
            val data = service.getHotList("weekly");
            emit(Resource.Success(data = data))
        }.flowOn(ioDispatcher);
    }

}