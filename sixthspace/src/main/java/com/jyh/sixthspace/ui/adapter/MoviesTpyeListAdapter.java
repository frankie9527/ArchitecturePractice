package com.jyh.sixthspace.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.ActivityMoviesTypeListItemBinding;
import com.jyh.sixthspace.sdk.bean.movie.VideoType;
import com.jyh.sixthspace.viewmodel.MoviesTypeListItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MoviesTpyeListAdapter extends RecyclerView.Adapter<MoviesTpyeListAdapter.MoviesTpyeListViewHolder> {
    private List<VideoType> list;


    public MoviesTpyeListAdapter() {
        this.list=new ArrayList<>();
    }

    @Override
    public MoviesTpyeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityMoviesTypeListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.activity_movies_type_list_item, parent, false);
        MoviesTpyeListViewHolder holder = new MoviesTpyeListViewHolder(binding);
        return holder;
    }


    @Override
    public void onBindViewHolder(MoviesTpyeListViewHolder holder, int position) {
        VideoType info = list.get(position);
        holder.setData(info);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MoviesTpyeListViewHolder extends RecyclerView.ViewHolder {
        private ActivityMoviesTypeListItemBinding binding;
        public MoviesTpyeListViewHolder(ActivityMoviesTypeListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void setData(VideoType info){
            if (binding.getViewModel()==null){
                binding.setViewModel(new MoviesTypeListItemViewModel(info));
            }else {
                binding.getViewModel().setData(info);
            }
        }
    }

    public void dataAppend(List<VideoType> list) {
        if (this.list==null){
            this.list=new ArrayList<>();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
