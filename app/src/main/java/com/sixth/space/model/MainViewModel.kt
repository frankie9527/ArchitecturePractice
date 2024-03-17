package com.sixth.space.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel  : ViewModel(){
    private val _isReady = MutableStateFlow(false)
    val go2main = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(360L)
            _isReady.value = true
        }
    }
}