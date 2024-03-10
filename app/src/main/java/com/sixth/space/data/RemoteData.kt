package com.sixth.space.data

import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.data.dao.VideoRepository
import com.sixth.space.data.video.CommentItem
import com.sixth.space.data.video.RecommendItem
import com.sixth.space.data.video.TikTokItem
import com.sixth.space.network.Resource
import com.sixth.space.network.RetrofitService
import com.sixth.space.network.error.NETWORK_ERROR
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.uitls.NetworkConnectivity
import com.sixth.space.uitls.commentList2Video
import com.sixth.space.uitls.hotList2Video
import com.sixth.space.uitls.recommendList2Video
import com.sixth.space.uitls.tiktokList2Video
import java.io.IOException
import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class RemoteData @Inject constructor(
    private val service: RetrofitService,
    private val networkConnectivity: NetworkConnectivity,
    private val repository: VideoRepository

) : RemoteDataSource {
    override suspend fun fetchHotList(str: String): Resource<List<VideoInfo>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data = service.fetchHotList(str);
            val newData = data.itemList.hotList2Video()

            repository.insertAll(newData)
            Resource.Success(data = newData)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchReplyComment(id: String): Resource<List<VideoInfo>> {
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
            val videoList = newData.commentList2Video();
            Resource.Success(data = videoList)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchRecommend(id: String): Resource<List<VideoInfo>> {
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
            Resource.Success(data = data.itemList.recommendList2Video())
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }

    override suspend fun fetchTiktokData(date: String, num: String): Resource<List<VideoInfo>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION);
        }
        return try {
            val data = service.fetchTiktok(date, num);
            val newData: ArrayList<TikTokItem> = data.itemList as ArrayList<TikTokItem>;
            val iterator = newData.iterator();
            while (iterator.hasNext()) {
                val tt = iterator.next()
                if (tt.type == "textCard") {
                    iterator.remove()
                }
            }
            data.itemList = newData;
            val videoList=data.itemList.tiktokList2Video();
            repository.insertAll(videoList)
            Resource.Success(data = videoList)
        } catch (e: IOException) {
            Resource.DataError(errorCode = NETWORK_ERROR)
        }
    }


}