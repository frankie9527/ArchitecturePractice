package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.dao.VideoInfo

import com.sixth.space.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.various.player.utils.LogUtils
import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 * private val dataRepositoryRepository: DataRepositorySource
 */
@HiltViewModel
class RemoteViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) :
    ViewModel() {
    val recipesHotDataPrivate = MutableLiveData<Resource<List<VideoInfo>>>()
    val recipesHotData: LiveData<Resource<List<VideoInfo>>> get() = recipesHotDataPrivate

    val recipesReplyDataPrivate = MutableLiveData<Resource<List<VideoInfo>>>()
    val recipesReplyData: LiveData<Resource<List<VideoInfo>>> get() = recipesReplyDataPrivate

    val recipesRecommendDataPrivate = MutableLiveData<Resource<List<VideoInfo>>>()
    val recipesRecommendData: LiveData<Resource<List<VideoInfo>>> get() = recipesRecommendDataPrivate


    val recipesTiktokDataPrivate = MutableLiveData<Resource<List<VideoInfo>>>()
    val recipesTiktokData: LiveData<Resource<List<VideoInfo>>> get() = recipesTiktokDataPrivate
    fun fetchHotData(position: Int) {
        LogUtils.d("RemoteViewModel","fetchHotData str="+position)
        var str: String;
        viewModelScope.launch {
            recipesHotDataPrivate.value = Resource.Loading();
            str = when (position) {
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
            dataRepositoryRepository.fetchHotList(str).collect() {
                recipesHotDataPrivate.value = it;
            }
        }
    }

    fun fetchReplyComment(id: String) {
        viewModelScope.launch {
            recipesReplyDataPrivate.value = Resource.Loading();
            dataRepositoryRepository.fetchReplyComment(id).collect() {
                recipesReplyDataPrivate.value = it;
            }
        }
    }

    fun fetchRecommend(id: String) {
        viewModelScope.launch {
            recipesReplyDataPrivate.value = Resource.Loading();
            dataRepositoryRepository.fetchRecommend(id).collect() {
                recipesRecommendDataPrivate.value = it;
            }
        }
    }
    /**
     * 推荐的时候 data  为 2015/06/01 至今随机一天
     *
     */
    fun fetchTiktokData(dataType:Int){

        var data="";
        var number=""
        if (dataType==0){
            data="1489107600000";
            number="2"
        }
        viewModelScope.launch {
            recipesTiktokDataPrivate.value = Resource.Loading();
            dataRepositoryRepository.fetchTiktokData(data,number).collect() {
                recipesTiktokDataPrivate.value = it;
            }
        }
    }
}