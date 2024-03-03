package com.sixth.space.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sixth.space.R
import org.easy.ui.recycler.base.BaseRecyclerAdapter
import org.easy.ui.recycler.base.BaseRecyclerHolder

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
class HotListAdapter  : BaseRecyclerAdapter<String, HotListAdapter.HotListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): HotListHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_hot_list_item, parent, false)
        return HotListHolder(view)
    }

    class HotListHolder(itemView: View) : BaseRecyclerHolder(itemView) {


        init {

        }
    }


}