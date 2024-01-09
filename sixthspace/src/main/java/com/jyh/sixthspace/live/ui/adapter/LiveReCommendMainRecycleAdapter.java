package com.jyh.sixthspace.live.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveCarousesBinding;
import com.jyh.sixthspace.databinding.FragmentLiveTypeBinding;
import com.jyh.sixthspace.live.ui.holder.RecommendCarouselHolder;
import com.jyh.sixthspace.live.ui.holder.RecommendCommonTypeHolder;
import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.bean.live.HomeFaceScoreColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/10/7.
 */

public class LiveReCommendMainRecycleAdapter extends RecyclerView.Adapter {
    public final int VIEWPAGER = 0;
    public final int HOT = 1;
    public final int BEAUTYS = 2;
    public final int OTHER = 3;
    HomeRecommendHotCate carousel = new HomeRecommendHotCate();
    HomeRecommendHotCate hot = new HomeRecommendHotCate();
    HomeRecommendHotCate beautys = new HomeRecommendHotCate();
    List<HomeRecommendHotCate> list = new ArrayList<HomeRecommendHotCate>();
    List<HomeCarousel> homeCarouselList;
    List<HomeHotColumn> homeHotList;
    LiveMainCarouselViewPagerAdapter carouselViewPagerAdapter;
    LiveReHotTypeAdapter hotTypeAdapter;
    LiveReOtherTypeAdapter otherTypeAdapter;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case VIEWPAGER:
                FragmentLiveCarousesBinding pagerBind = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_live_carouses, parent, false);
                RecommendCarouselHolder carouselHolder = new RecommendCarouselHolder(pagerBind.getRoot());
                carouselHolder.setBind(pagerBind);
                holder = carouselHolder;
                break;
            case HOT:
                FragmentLiveTypeBinding hotBind = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_live_type, parent, false);
                RecommendCommonTypeHolder commonTypeHolder = new RecommendCommonTypeHolder(hotBind.getRoot());
                commonTypeHolder.setBind(hotBind);
                holder = commonTypeHolder;
                break;
            case BEAUTYS:
                break;
            case OTHER:
                FragmentLiveTypeBinding otherBind = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_live_type, parent, false);
                RecommendCommonTypeHolder otherTypeHolder = new RecommendCommonTypeHolder(otherBind.getRoot());
                otherTypeHolder.setBind(otherBind);
                holder = otherTypeHolder;
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEWPAGER:
                RecommendCarouselHolder carouselHolder = (RecommendCarouselHolder) holder;
                if (carouselHolder.getBind().liveRecommendCarouse.getAdapter() == null) {
                    carouselViewPagerAdapter = new LiveMainCarouselViewPagerAdapter();
                    carouselHolder.initIndicator(carouselViewPagerAdapter);
                }
                carouselHolder.setCircleCount(homeCarouselList.size());
                carouselViewPagerAdapter.setData(homeCarouselList);
                break;
            case HOT:
                RecommendCommonTypeHolder commonTypeHolder = (RecommendCommonTypeHolder) holder;
//                if (commonTypeHolder.getBind().recyclerTypeList.getAdapter() == null) {
                    hotTypeAdapter = new LiveReHotTypeAdapter();
                    commonTypeHolder.SetAdapter(hotTypeAdapter);
//                }
                hotTypeAdapter.setData(homeHotList);
                commonTypeHolder.getBind().imgTypeIcon.setImageResource(R.mipmap.reco_game_txt_icon);
                commonTypeHolder.getBind().tvTypeName.setText("最热");
                break;
            case BEAUTYS:
                break;
            case OTHER:
                RecommendCommonTypeHolder otherTypeHolder = (RecommendCommonTypeHolder) holder;
                otherTypeAdapter=new LiveReOtherTypeAdapter();
                otherTypeHolder.SetAdapter(otherTypeAdapter);
                otherTypeAdapter.setData(list.get(position).getRoom_list());
                otherTypeHolder.getBind().imgTypeIcon.setImageResource(R.mipmap.icon_column);
                otherTypeHolder.getBind().tvTypeName.setText(list.get(position).getTag_name());

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }


    public void setCarouselData(List<HomeCarousel> list) {
        carousel.setType(VIEWPAGER);
        if (list != null) {
            this.list.add(0, carousel);
        } else {
            this.list.remove(carousel);
        }
        homeCarouselList = list;
        notifyDataSetChanged();
    }


    public void setHotData(List<HomeHotColumn> list) {
        hot.setType(HOT);
        if (list != null) {
            this.list.add(hot);
        } else {
            this.list.remove(hot);
        }
        homeHotList = list;
        notifyDataSetChanged();
    }


    public void setBeautysData(List<HomeFaceScoreColumn> list) {
//        beautys.setType(BEAUTYS);
//        if (list != null) {
//            this.list.add(2,beautys);
//        } else {
//            this.list.remove(beautys);
//        }

//        notifyDataSetChanged();
    }


    public void setOtherData(List<HomeRecommendHotCate> list) {
        if (list != null) {
            this.list.addAll(list);
        } else {
            this.list.removeAll(list);
        }
        notifyDataSetChanged();
    }
}
