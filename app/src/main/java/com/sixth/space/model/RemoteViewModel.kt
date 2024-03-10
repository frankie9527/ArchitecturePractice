package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.base.HttpResponse
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.data.dao.VideoRepository
import com.sixth.space.data.video.HotItem
import com.sixth.space.data.video.RecommendItem
import com.sixth.space.data.video.CommentItem

import com.sixth.space.network.Resource
import com.sixth.space.uitls.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    fun fetchTiktokData(dataType:Int){
        viewModelScope.launch {
            recipesTiktokDataPrivate.value = Resource.Loading();
            dataRepositoryRepository.fetchTiktokData("","").collect() {
                recipesTiktokDataPrivate.value = it;
            }
        }
    }
}