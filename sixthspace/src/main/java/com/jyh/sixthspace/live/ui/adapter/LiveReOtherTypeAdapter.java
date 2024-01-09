package com.jyh.sixthspace.live.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveTypeItemBinding;
import com.jyh.sixthspace.live.viewmodel.LiveReHotViewModel;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/10/9.
 */

public class LiveReOtherTypeAdapter extends RecyclerView.Adapter<LiveReOtherTypeAdapter.LiveReOtherViewHolder> {
    List<HomeRecommendHotCate.RoomListEntity> list = new ArrayList<>();
    FragmentLiveTypeItemBinding itemBinding;

    @Override
    public LiveReOtherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_live_type_item, parent, false);
        LiveReOtherViewHolder hotViewHolder = new LiveReOtherViewHolder(itemBinding.getRoot());
        hotViewHolder.setBind(itemBinding);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(LiveReOtherViewHolder holder, int position) {
        holder.setData(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LiveReOtherViewHolder extends RecyclerView.ViewHolder {
        private FragmentLiveTypeItemBinding bind;


        public LiveReOtherViewHolder(View itemView) {
            super(itemView);
        }

        public void setBind(FragmentLiveTypeItemBinding binding) {
            this.bind = binding;

        }

        public void setData(final HomeRecommendHotCate.RoomListEntity info) {
            if (bind.getModel() == null) {
                bind.setModel(new LiveReHotViewModel());
            }
            bind.getModel().setData(info);
        }
    }

    public void setData(List<HomeRecommendHotCate.RoomListEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }
}
