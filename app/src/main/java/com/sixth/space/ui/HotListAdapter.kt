package com.sixth.space.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sixth.space.base.Constant
import com.sixth.space.data.HotItem
import com.sixth.space.data.ReplyItem
import com.sixth.space.databinding.FragmentHotListItemBinding
import com.sixth.space.databinding.FragmentReplyListItemBinding
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder
import java.util.Objects

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class HotListAdapter(private val currentP: Int) :
    BaseRecyclerAdapter<Objects, BaseRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseRecyclerHolder {
        if (getItemViewType(position) == Constant.recycler_type_hot) {
            val hotBinding =
                FragmentHotListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HotListHolder(hotBinding)
        }
        val replyBinding =
            FragmentReplyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RePlyHolder(replyBinding)
    }

    override fun MyHolder(holder: BaseRecyclerHolder, position: Int) {
        super.MyHolder(holder, position)
        if (getItemViewType(position) == Constant.recycler_type_hot) {
            val data = list.get(position) as HotItem;
            val hotHolder = holder as HotListHolder;
            hotHolder.bin(data)
        }
        if (getItemViewType(position) == Constant.recycler_type_reply) {
            val data = list.get(position) as ReplyItem;
            val replyHolder = holder as RePlyHolder;
            replyHolder.bin(data);
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (currentP == 0 || currentP == 1 || currentP == 2) {
            return Constant.recycler_type_hot
        } else if (position == 3) {
            return Constant.recycler_type_recommend
        }
        return Constant.recycler_type_reply
    }


    class HotListHolder(private val itemBinding: FragmentHotListItemBinding) :
        BaseRecyclerHolder(itemBinding.root) {
        fun bin(data: HotItem) {
            itemBinding.title.setText(data.data.title)
            itemBinding.type.setText("#" + data.data.category)
            Glide.with(itemBinding.img.context)
                .load(data.data.cover.feed)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemBinding.img)
        }
    }

    class RePlyHolder(private val itemBinding: FragmentReplyListItemBinding) :
        BaseRecyclerHolder(itemBinding.root) {
        fun bin(data: ReplyItem) {
            Glide.with(itemBinding.imgHead.context).load(data.data.cover).into(itemBinding.imgHead);
            itemBinding.tvMessage.setText(data.data.message);
//            itemBinding.tvLikeCount.setText(data.data.likeCount);
            itemBinding.tvNickname.setText("nihao")
        }
    }

}