package com.sixth.space.model


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.dao.VideoInfo

import com.sixth.space.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 * private val dataRepositoryRepository: DataRepositorySource
 *
 */
@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val dataRepositoryRepository: DataRepositorySource,
    var info: VideoInfo
) :
    ViewModel() {
    val homeState = MutableStateFlow<Resource<List<VideoInfo>>?>(null)
    val hotState = MutableStateFlow<Resource<List<VideoInfo>>?>(null)
    val replyState = MutableStateFlow<Resource<List<VideoInfo>>?>(null)
    val recommendState = MutableStateFlow<Resource<List<VideoInfo>>?>(null)
    /**
     *  page:0  homeDailyState
     *  page:1  homeRecommendState
     * */
    fun fetchHomeState(page: Int) {
        var data = "";
        var number = ""
        if (page == 0) {
            data = "1489107600000";
            number = "2"
        }
        viewModelScope.launch {
            dataRepositoryRepository.fetchHomeData(data,number).collect() {
                homeState.value=it;
            }
        }
    }


    /**
     *  page:0  weekly
     *  page:1  monthly
     *  page:2  historical
     * */
    fun fetchHotState(page: Int) {
        val str = when (page) {
            0 -> {
                "weekly"
            }

            1 -> {
                "monthly"
            }

            else -> {
                "historical"
            }
        }
        viewModelScope.launch {
            dataRepositoryRepository
                .fetchHotList(str).collect() {
                    hotState.value=it
                }
        }
    }

    fun fetchReplyState() {
        val id=info.videoId;
        viewModelScope.launch {
            dataRepositoryRepository.fetchReplyList(id.toString()).collect() {
                replyState.value=it
            }
        }
    }
    fun fetchRecommend() {
        val id=info.videoId;
        viewModelScope.launch {
            dataRepositoryRepository.fetchRecommend(id.toString()).collect() {
                recommendState.value=it
            }
        }
    }
}