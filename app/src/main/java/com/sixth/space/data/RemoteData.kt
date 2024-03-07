package com.sixth.space.data

import com.sixth.space.base.HttpResponse
import com.sixth.space.network.Resource
import com.sixth.space.network.RetrofitService
import com.sixth.space.network.error.NETWORK_ERROR
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.uitls.LogUtils
import com.sixth.space.uitls.NetworkConnectivity
import com.sixth.space.uitls.commentList2Video
import com.sixth.space.uitls.hotList2Video
import com.sixth.space.uitls.recommendList2Video
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
            val data = service.fetchHotList(str);
            val newData = data.itemList.hotList2Video()
            data.videoList = newData;
            Resource.Success(data = data)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchReplyComment(id: String): Resource<HttpResponse<CommentItem>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data = service.fetchReply("", id, "", "");
            val newData = data.itemList as ArrayList<CommentItem>;
            val iterator = newData.iterator();
            while (iterator.hasNext()) {
                val tt = iterator.next()
                if (tt.type != "reply") {
                    iterator.remove()
                }
            }
            data.itemList = newData;
            data.videoList=newData.commentList2Video();
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
            LogUtils.d("aaa","data size="+newData.size)
            while (iterator.hasNext()) {
                val tt = iterator.next()
//                LogUtils.d("aaa","tt type="+tt.type)
                if (tt.type == "textCard") {
                    LogUtils.d("aaa","tt type= wojinlaile")
                    iterator.remove()
                }
            }

            data.itemList = newData;
            LogUtils.d("aaa","new data size="+data.itemList.size)
             data.videoList=data.itemList.recommendList2Video()
            Resource.Success(data = data)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }
}