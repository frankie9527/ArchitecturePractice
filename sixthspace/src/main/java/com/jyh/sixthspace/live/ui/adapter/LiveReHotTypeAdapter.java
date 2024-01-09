package com.jyh.sixthspace.live.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveTypeItemBinding;
import com.jyh.sixthspace.live.viewmodel.LiveReHotViewModel;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/10/9.
 */

public class LiveReHotTypeAdapter extends RecyclerView.Adapter<LiveReHotTypeAdapter.LiveReHotViewHolder> {
    List<HomeHotColumn> list = new ArrayList<>();
    FragmentLiveTypeItemBinding itemBinding;
    @Override
    public LiveReHotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_live_type_item, parent, false);
        LiveReHotViewHolder hotViewHolder = new LiveReHotViewHolder(itemBinding.getRoot());
        hotViewHolder.setBind(itemBinding);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(LiveReHotViewHolder holder, int position) {
        holder.setData(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LiveReHotViewHolder extends RecyclerView.ViewHolder {
        private FragmentLiveTypeItemBinding bind;
        public LiveReHotViewHolder(View itemView) {
            super(itemView);
        }

        public void setBind(FragmentLiveTypeItemBinding binding) {
            this.bind = binding;

        }

        public void setData(HomeHotColumn info) {
            if (bind.getModel() == null) {
                bind.setModel(new LiveReHotViewModel());
            }
            bind.getModel().setData(info);
        }
    }

    public void setData(List<HomeHotColumn> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }
}
