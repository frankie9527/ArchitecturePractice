package com.sixth.space.model

import android.provider.MediaStore.Video
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.base.HttpResponse
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.data.dao.VideoRepository
import com.sixth.space.data.video.HotItem
import com.sixth.space.data.video.RecommendItem
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NETWORK_ERROR
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.uitls.LogUtils
import com.sixth.space.uitls.hotList2Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.IOException

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
                LogUtils.d("jyh","data="+data.size);
                emit(Resource.Success(data = data))
            }.flowOn(Dispatchers.IO).collect() {
                recipesDataPrivate.value = it;
            }
        }
    }


}