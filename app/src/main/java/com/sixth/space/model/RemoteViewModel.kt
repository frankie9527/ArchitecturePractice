package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.HotList
import com.sixth.space.data.ReplyList
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
    val recipesHotDataPrivate = MutableLiveData<Resource<HotList>>()
    val recipesHotData: LiveData<Resource<HotList>> get() = recipesHotDataPrivate

    val recipesReplyDataPrivate = MutableLiveData<Resource<ReplyList>>()
    val recipesReplyData: LiveData<Resource<ReplyList>> get() = recipesReplyDataPrivate


    fun fetchHotData(position: Int) {
        var str: String;
        viewModelScope.launch {
            recipesHotDataPrivate.value = Resource.Loading();
            str = when (position) {
                0 -> {
                    "monthly"
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