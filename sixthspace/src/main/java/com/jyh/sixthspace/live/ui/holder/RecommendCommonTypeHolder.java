package com.jyh.sixthspace.live.ui.holder;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.databinding.FragmentLiveTypeBinding;


/**
 * Created by Administrator on 2017/10/9.
 */

public class RecommendCommonTypeHolder extends RecyclerView.ViewHolder {
    FragmentLiveTypeBinding binding;
    GridLayoutManager gridLayoutManager;
    public RecommendCommonTypeHolder(View itemView) {
        super(itemView);
    }

    public void setBind(FragmentLiveTypeBinding binding) {
        this.binding = binding;
    }

    public FragmentLiveTypeBinding getBind() {
        return binding;
    }

    public void SetAdapter(RecyclerView.Adapter adapter) {
        gridLayoutManager = new GridLayoutManager(binding.recyclerTypeList.getContext(), 2);
        binding.recyclerTypeList.setLayoutManager(gridLayoutManager);
        binding.recyclerTypeList.setAdapter(adapter);
    }
}
