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
import com.sixth.space.data.ReplyItem
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
    BaseRecyclerAdapter<Objects, BaseRecyclerHolder>() {

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
            val data = list.get(position) as HotItem;
            val hotHolder = holder as HotListHolder;
            hotHolder.bin(data)
        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_comment) {
            val data = list.get(position) as ReplyItem;
            val replyHolder = holder as CommentHolder;
            replyHolder.bin(data);
        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_recommend_head) {
            val data = list.get(position) as RecommendItem;
            val headHolder = holder as RecommendHeadHolder;
            headHolder.bin(data)

        }
        if (getItemViewType(position) == Constant.recycler_adapter_type_recommend) {
            val data = list.get(position) as RecommendItem;
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
        fun bin(data: HotItem) {
            binding.title.setText(data.data.title)
            binding.type.setText("#" + data.data.category)
            binding.img.let {
                Glide.with(it)
                    .load(data.data.cover.feed)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(it)
            }
        }
    }

    class CommentHolder(private val binding: FragmentCommentListItemBinding) :
        BaseRecyclerHolder(binding.root) {
        fun bin(data: ReplyItem) {
            binding.imgHead.let {
                Glide.with(it).load(data.data.user.avatar).into(it);
            }
            binding.tvMessage.text = data.data.message;
            binding.tvLikeCount.text = data.data.likeCount.toString();
            binding.tvNickname.text = data.data.user.nickname
            binding.tvDate.text = data.data.createTime.getTime2String()
        }
    }

    class RecommendHolder(private val binding: FragmentRecommendListItemBinding) :
        BaseRecyclerHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bin(data: RecommendItem) {
            binding.imgCover.let {
                Glide.with(it).load(data.data.cover?.feed).into(it);
            }
            binding.tvTitle.text = data.data.title;
            binding.tvCategory.text = "#" + data.data?.category;
            binding.tvAuthor.text = data.data.author?.name;
            binding.tvDate.text = data.data.releaseTime.getTime2String()
            binding.tvDuration.text = data.data.duration.durationToStr();
        }
    }

    class RecommendHeadHolder(private val binding: FragmentRecommendHeadItemBinding) :
        BaseRecyclerHolder(binding.root) {
        fun bin(data: RecommendItem) {

        }
    }
}