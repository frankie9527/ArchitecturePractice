package com.sixth.space.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sixth.space.base.Constant
import com.sixth.space.data.HotItem
import com.sixth.space.data.RecommendItem
import com.sixth.space.data.CommentItem
import com.sixth.space.data.VideoInfo
import com.sixth.space.databinding.FragmentCommentListItemBinding
import com.sixth.space.databinding.FragmentHotListItemBinding
import com.sixth.space.databinding.FragmentRecommendHeadItemBinding
import com.sixth.space.databinding.FragmentRecommendListItemBinding
import com.sixth.space.uitls.durationToStr
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder
import com.sixth.space.uitls.getTime2String
import java.util.Objects


/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class HotAndVideoAdapter(private val adapterType: Int) :
    BaseRecyclerAdapter<VideoInfo, BaseRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val binding: ViewBinding;
        if (viewType == Constant.recycler_adapter_type_hot) {
            binding =
                FragmentHotListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HotListHolder(binding)
        }
        if (viewType == Constant.recycler_adapter_type_comment) {
            binding =
                FragmentCommentListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CommentHolder(binding)
        }
        if (viewType == Constant.recycler_adapter_type_recommend_head) {
            binding =
                FragmentRecommendHeadItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return RecommendHeadHolder(binding)
        }
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
        if (getItemViewType(position) == Constant.recycler_adapter_type_hot) {
            val data = list.get(position);
            val hotHolder = holder as HotListHolder;
            hotHolder.bin(data)
        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_comment) {
            val data = list.get(position);
            val replyHolder = holder as CommentHolder;
            replyHolder.bin(data);
        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_recommend_head) {
            val data = list.get(position)
            val headHolder = holder as RecommendHeadHolder;
            headHolder.bin(data)

        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_recommend) {
            val data = list.get(position)
            val recommendHolder = holder as RecommendHolder;
            recommendHolder.bin(data)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (adapterType == 0 || adapterType == 1 || adapterType == 2) {
            return Constant.recycler_adapter_type_hot
        } else if (adapterType == 3) {
            if (position == 0) {
                return Constant.recycler_adapter_type_recommend_head
            }
            return Constant.recycler_adapter_type_recommend
        }
        return Constant.recycler_adapter_type_comment
    }


    class HotListHolder(private val binding: FragmentHotListItemBinding) :
        BaseRecyclerHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bin(data: VideoInfo) {
            binding.title.setText(data.title)
            binding.type.setText("#" + data.category)
            binding.img.let {
                Glide.with(it)
                    .load(data.cover)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(it)
            }
        }
    }

    class CommentHolder(private val binding: FragmentCommentListItemBinding) :
        BaseRecyclerHolder(binding.root) {
        fun bin(data: VideoInfo) {

            binding.imgHead.let {
                Glide.with(it).load(data.avatar).into(it);
            }
            binding.tvMessage.text = data.commentMsg;
            binding.tvLikeCount.text = data.likeCount.toString();
            binding.tvNickname.text = data.name
            binding.tvDate.text = data.releaseTime.getTime2String()
        }
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
            binding.tvAuthor.text = data.name;
            binding.tvDate.text = data.releaseTime.getTime2String()
            binding.tvDuration.text = data.duration.durationToStr();
        }
    }

    class RecommendHeadHolder(private val binding: FragmentRecommendHeadItemBinding) :
        BaseRecyclerHolder(binding.root) {
        fun bin(data: VideoInfo) {

        }
    }
}