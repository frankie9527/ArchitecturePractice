package com.sixth.space.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 */
@HiltViewModel
class HttpViewModel @Inject constructor(private val service: RetrofitService) : ViewModel() {

    fun getVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = service.getHotList("weekly");
            } catch (error:  Exception) {
                Log.e("HttpViewModel",error.toString());
            }
        }
    }
}