package com.sixth.space.ui.activity

import android.os.Bundle
import com.sixth.space.base.BaseActivity
import com.sixth.space.databinding.ActivitySearchBinding

/**
 * @author: Frankie
 * @Date: 2024/3/8
 * @Description:
 */
class SearchActivity :BaseActivity() {
    val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }
    override fun observeViewModel() {

    }

    override fun initViewBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}