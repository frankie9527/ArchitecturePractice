package com.sixth.space.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sixth.space.data.HotList
import com.sixth.space.databinding.FragmentHotListItemBinding
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class HotListAdapter : BaseRecyclerAdapter<HotList.Item, HotListAdapter.HotListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): HotListHolder {
        val itemBinding =
            FragmentHotListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotListHolder(itemBinding)
    }

    override fun MyHolder(holder: HotListHolder, position: Int) {
        super.MyHolder(holder, position)
        holder.bin(list.get(position))
    }


    class HotListHolder(private val itemBinding: FragmentHotListItemBinding) :
        BaseRecyclerHolder(itemBinding.root) {
        fun bin(data: HotList.Item) {
            itemBinding.title.setText(data.data.title)
            itemBinding.type.setText("#" +data.data.category)
            Glide.with(itemBinding.img.context)
                .load(data.data.cover.feed)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemBinding.img)
        }
    }


}