package com.sixth.space.data

import com.sixth.space.base.BaseResp
import com.sixth.space.network.Resource
import com.sixth.space.network.RetrofitService
import com.sixth.space.network.error.NETWORK_ERROR
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.uitls.LogUtils
import com.sixth.space.uitls.NetworkConnectivity
import java.io.IOException
import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class RemoteData @Inject constructor(
    private val service: RetrofitService,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {
    override suspend fun getHotList(str: String): Resource<BaseResp<HotItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            LogUtils.d("RemoteData","getHotList str="+str)
            Resource.Success(data = service.getHotList(str))
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchReplyComment(id: String): Resource<BaseResp<ReplyItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data=service.getReply("",id,"","");
            Resource.Success(data =data )
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }
}