package com.sixth.space.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sixth.space.R
import com.sixth.space.databinding.ActivityTestBinding
import com.sixth.space.model.SearchViewModel
import com.sixth.space.model.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    val viewModel: SearchViewModel by viewModels()
    private var menu: View? = null
    val binding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }
    fun demo1(view: View){
        viewModel.search("b")
    }
}
