package com.sixth.space.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.HotList
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
class HotViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) :
    ViewModel() {
    val recipesLiveDataPrivate = MutableLiveData<Resource<HotList>>()
    val recipesLiveData: LiveData<Resource<HotList>> get() = recipesLiveDataPrivate


    lateinit var str: String;
    fun fetchHotData(position: Int) {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading();
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
                recipesLiveDataPrivate.value = it;
            }

        }
    }

}