package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.base.HttpResponse
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.HotItem
import com.sixth.space.data.RecommendItem
import com.sixth.space.data.ReplyItem

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
    val recipesHotDataPrivate = MutableLiveData<Resource<HttpResponse<HotItem>>>()
    val recipesHotData: LiveData<Resource<HttpResponse<HotItem>>> get() = recipesHotDataPrivate

    val recipesReplyDataPrivate = MutableLiveData<Resource<HttpResponse<ReplyItem>>>()
    val recipesReplyData: LiveData<Resource<HttpResponse<ReplyItem>>> get() = recipesReplyDataPrivate

    val recipesRecommendDataPrivate = MutableLiveData<Resource<HttpResponse<RecommendItem>>>()
    val recipesRecommendData: LiveData<Resource<HttpResponse<RecommendItem>>> get() = recipesRecommendDataPrivate


    fun fetchHotData(position: Int) {
        LogUtils.d("RemoteViewModel","fetchHotData str="+position)
        Thread.dumpStack();
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


}