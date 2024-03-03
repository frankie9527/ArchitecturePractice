package com.sixth.space.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepository
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.HotList
import com.sixth.space.data.Resource
import com.sixth.space.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
@HiltViewModel
class TestViewModel @Inject constructor() :  ViewModel() {
    val recipesLiveDataPrivate = MutableLiveData<Resource<HotList>>()
    val recipesLiveData: LiveData<Resource<HotList>> get() = recipesLiveDataPrivate
    fun fetchHotData() {
    }
    fun demo(){
//       viewModelScope.launch {
//           recipesLiveDataPrivate.value = Resource.Loading();
//           dataRepositoryRepository.getHotList(0).collect(){
//               recipesLiveDataPrivate.value=it;
//           }
//       }
    }
}