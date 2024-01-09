package com.jyh.sixthspace.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentRecommendRecyclerItemBinding;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.viewmodel.RecommendRecyclerItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class RecommendRecyclerAdapter extends RecyclerView.Adapter<RecommendRecyclerAdapter.RecommendViewHolder> {
    List<VideoInfo> list;

    public RecommendRecyclerAdapter() {
        this.list = Collections.emptyList();
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentRecommendRecyclerItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_recommend_recycler_item,
                        parent, false);
        RecommendViewHolder holder = new RecommendViewHolder(binding.getRoot());

        holder.setBind(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        private FragmentRecommendRecyclerItemBinding binding;

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        public void setBind(FragmentRecommendRecyclerItemBinding binding) {
            this.binding = binding;
        }

        public void setData(VideoInfo info) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new RecommendRecyclerItemViewModel(info));
            } else {
                binding.getViewModel().setData(info);
            }
        }

    }

    public void setData(List<VideoInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
