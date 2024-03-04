package com.sixth.space.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.sixth.space.base.BaseActivity
import com.sixth.space.base.Constant
import com.sixth.space.data.HotList
import com.sixth.space.databinding.ActivityVideoDetailsBinding

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class VideoDetailsActivity : BaseActivity() {
    lateinit var videoData:HotList.Item;
    val binding by lazy {
        ActivityVideoDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoData = if (Build.VERSION.SDK_INT>Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra(Constant.VIDEO_ITEM,HotList.Item::class.java) as HotList.Item
        }else{
            intent.getSerializableExtra(Constant.VIDEO_ITEM) as HotList.Item
        }


    }
    override fun observeViewModel() {

    }

    override fun initViewBinding() {

    }
}