package com.sixth.space.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentRecommendListItemBinding
import com.sixth.space.uitls.durationToStr
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder
import com.sixth.space.uitls.getTime2String
import org.various.player.utils.LogUtils


/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class SearchAdapter() :
    BaseRecyclerAdapter<VideoInfo, BaseRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val binding: ViewBinding;
        binding =
            FragmentRecommendListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RecommendHolder(binding)

    }

    override fun MyHolder(holder: BaseRecyclerHolder, position: Int) {
        super.MyHolder(holder, position)
        val data = list.get(position)
        val recommendHolder = holder as RecommendHolder;
        recommendHolder.bin(data)

    }





    class RecommendHolder(private val binding: FragmentRecommendListItemBinding) :
        BaseRecyclerHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bin(data: VideoInfo) {
            binding.imgCover.let {
                Glide.with(it).load(data.cover).into(it);
            }
            binding.tvTitle.text = data.title;
            binding.tvCategory.text = "#" + data.category;
            binding.tvAuthor.text = data.user_name;
            binding.tvDate.text = data.releaseTime.getTime2String()
            binding.tvDuration.text = data.duration.durationToStr();
        }
    }


}