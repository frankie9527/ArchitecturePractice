package com.jyh.sixthspace.ui.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentMoviesRecyclerItemBinding;
import com.jyh.sixthspace.sdk.bean.movie.VideoInfo;
import com.jyh.sixthspace.viewmodel.MoviesRecyclerItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesRecyclerViewHolder> {
    private List<VideoInfo> list;


    public MoviesRecyclerAdapter() {
        this.list = Collections.emptyList();
    }

    @Override
    public MoviesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentMoviesRecyclerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_movies_recycler_item, parent, false);
        MoviesRecyclerViewHolder holder = new MoviesRecyclerViewHolder(binding.getRoot());

        holder.setBind(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoviesRecyclerViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MoviesRecyclerViewHolder extends RecyclerView.ViewHolder {
        FragmentMoviesRecyclerItemBinding binding;

        public MoviesRecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public void setBind(FragmentMoviesRecyclerItemBinding binding) {
            this.binding = binding;
        }

        public void setData(VideoInfo info) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new MoviesRecyclerItemViewModel(info));
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
