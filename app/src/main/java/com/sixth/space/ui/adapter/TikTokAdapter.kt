package com.sixth.space.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sixth.space.R
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentTiktokItemBinding
import com.sixth.space.ui.player.TiktokVideoView
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
        tikTokHolder.bin(data)

    }

    var recyclerView: RecyclerView? = null
    fun setRecycler(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView;
    }

    fun getVideoView(position: Int): TiktokVideoView? {
        val viewHolder = recyclerView!!.findViewHolderForAdapterPosition(position)
        return if (viewHolder != null && viewHolder is TikTokHolder) {
            viewHolder.itemView.findViewById(R.id.video_view);
        } else {
            null
        }

    }

    class TikTokHolder(private val binding: FragmentTiktokItemBinding) :
        BaseRecyclerHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bin(data: VideoInfo) {
            binding.videoView.setVideoInfo(data)
            binding.videoView.image_share.setOnClickListener(this)
            binding.videoView.image_comment.setOnClickListener(this)
        }
    }


}