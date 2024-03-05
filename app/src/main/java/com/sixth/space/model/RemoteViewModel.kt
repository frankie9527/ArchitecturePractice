package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.base.BaseResp
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.HotItem
import com.sixth.space.data.ReplyItem

import com.sixth.space.network.Resource
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
    val recipesHotDataPrivate = MutableLiveData<Resource<BaseResp<HotItem>>>()
    val recipesHotData: LiveData<Resource<BaseResp<HotItem>>> get() = recipesHotDataPrivate

    val recipesReplyDataPrivate = MutableLiveData<Resource<BaseResp<ReplyItem>>>()
    val recipesReplyData: LiveData<Resource<BaseResp<ReplyItem>>> get() = recipesReplyDataPrivate


    fun fetchHotData(position: Int) {
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
            dataRepositoryRepository.getHotList(str).collect() {
                recipesHotDataPrivate.value = it;
            }
        }
    }

    fun videoRecommend() {}

    fun fetchReplyComment(id: String) {
        viewModelScope.launch {
            recipesReplyDataPrivate.value = Resource.Loading();
            dataRepositoryRepository.fetchReplyComment(id).collect() {
                recipesReplyDataPrivate.value = it;
            }
        }
    }


}