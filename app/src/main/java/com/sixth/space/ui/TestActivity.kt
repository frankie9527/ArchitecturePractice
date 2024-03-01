package com.sixth.space.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sixth.space.R
import com.sixth.space.model.HttpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {



    val viewModel: HttpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }

    fun hotList(view: View) {
        viewModel.getVideoList()
    }

    fun monthly(view: View) {

    }
    fun historical(view: View) {

    }


}
