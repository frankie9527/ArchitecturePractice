package com.sixth.space.data

import android.util.Log
import com.sixth.space.base.HttpResponse
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
    override suspend fun fetchHotList(str: String): Resource<HttpResponse<HotItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            Resource.Success(data = service.fetchHotList(str))
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchReplyComment(id: String): Resource<HttpResponse<ReplyItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data = service.fetchReply("", id, "", "");
            val newData = data.itemList.subList(1, data.itemList.size);
            data.itemList = newData;
            Resource.Success(data = data)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchRecommend(id: String): Resource<HttpResponse<RecommendItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data = service.fetchRecommend(id)
            val newData: ArrayList<RecommendItem> = data.itemList as ArrayList<RecommendItem>;
            val iterator = newData.iterator();
            while (iterator.hasNext()) {
                val tt = iterator.next()
                if (tt.type == "textCard") {
                    iterator.remove()
                }
            }
            data.itemList = newData;
            Resource.Success(data = data)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }
}