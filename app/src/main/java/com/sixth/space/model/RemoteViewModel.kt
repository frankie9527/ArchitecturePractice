package com.sixth.space.model


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.dao.VideoDetailsInfo
import com.sixth.space.data.dao.VideoInfo

import com.sixth.space.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

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
    val videoInfo: VideoDetailsInfo
) :
    ViewModel() {
    val homeDailyState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchTiktokData("1489107600000", "2")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )
    val homeRecommendState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchTiktokData("", "")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    /**
     *  page:0  homeDailyState
     *  page:1  homeRecommendState
     * */
    fun homeState(page: Int): Flow<Resource<List<VideoInfo>>?> {
        Log.e("jyh", "homeState page $page")
        var data = "";
        var number = ""
        if (page == 0) {
            data = "1489107600000";
            number = "2"
        }
        return dataRepositoryRepository
            .fetchTiktokData(data, number)

    }

    val hotWeeklyState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchHotList("weekly")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )
    val hotMonthlyState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchHotList("monthly")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )
    val hotHistoricalState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchHotList("historical")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    /**
     *  page:0  weekly
     *  page:1  monthly
     *  page:2  historical
     * */
    fun hotState(page: Int): StateFlow<Resource<List<VideoInfo>>?> {
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
        return dataRepositoryRepository
            .fetchHotList(str)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null,
            )
    }
}