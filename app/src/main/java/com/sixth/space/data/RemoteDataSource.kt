package com.sixth.space.data

import com.sixth.space.network.Resource


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
interface RemoteDataSource {
    suspend fun getHotList(str: String): Resource<HotList>
}