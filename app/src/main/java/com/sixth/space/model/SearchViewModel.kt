package com.sixth.space.model

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.data.dao.VideoRepository
import com.sixth.space.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/3/9
 * @Description:
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: VideoRepository) : ViewModel() {
    val recipesDataPrivate = MutableLiveData<Resource<List<VideoInfo>>>()
    val recipesData: LiveData<Resource<List<VideoInfo>>> get() = recipesDataPrivate
    fun search(str: String) {
        viewModelScope.launch {
            recipesDataPrivate.value = Resource.Loading();
            flow<Resource<List<VideoInfo>>> {
                val data: List<VideoInfo>;
                data = if (TextUtils.isEmpty(str)) {
                    repository.getAll();
                } else {
                    repository.getVideoByKeyWord(str);
                }
                emit(Resource.Success(data = data))
            }.flowOn(Dispatchers.IO).collect() {
                recipesDataPrivate.value = it;
            }
        }
    }


}