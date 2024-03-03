package com.sixth.space.data

import kotlinx.coroutines.flow.Flow

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
interface DataRepositorySource {
    suspend fun getHotList(position: Int): Flow<Resource<HotList>>
}