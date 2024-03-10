package com.sixth.space.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentTiktokItemBinding
import com.sixth.space.uitls.LogUtils
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder


/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class TikTokAdapter() :
    BaseRecyclerAdapter<VideoInfo, BaseRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val binding: ViewBinding;
        binding =
            FragmentTiktokItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TikTokHolder(binding)

    }

    override fun MyHolder(holder: BaseRecyclerHolder, position: Int) {
        super.MyHolder(holder, position)
        val data = list.get(position)
        val tikTokHolder = holder as TikTokHolder;
        tikTokHolder.bin(data, position)

    }


    class TikTokHolder(private val binding: FragmentTiktokItemBinding) :
        BaseRecyclerHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bin(data: VideoInfo, position: Int) {
            LogUtils.d("jyh", "TikTokHolder" + position);
            binding.tv.text = data.title
        }
    }


}