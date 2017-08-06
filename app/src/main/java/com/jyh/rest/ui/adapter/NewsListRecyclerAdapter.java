package com.jyh.rest.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyh.rest.R;

import com.jyh.rest.base.Constant;
import com.jyh.rest.base.RecyclerViewBaseHolder;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.ui.holder.ImgExtraHolder;
import com.jyh.rest.ui.holder.NewsCommonHolder;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.utils.UIUtils;
import com.jyh.rest.utils.spUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class NewsListRecyclerAdapter extends RecyclerView.Adapter {
    private List<NewsBean> list = new ArrayList<>();
    private ItemClickListener mListener;

    /**
     * 横向多张图的adapter
     */
    public ImgExtraHorizontalAdapter imgExtraHorizontalAdapter;


    /**
     * 横向多张图layoutManager参数设置
     */
    private LinearLayoutManager layoutManagerImgExtra;



    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerViewBaseHolder holder = null;
        switch (viewType) {
            case Constant.NEWS_IMGEXTRA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_imgexrtra_horizontal, parent, false);
                holder = new ImgExtraHolder(view);
                break;
            case Constant.NEWS_NORAML:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_common_item, parent, false);
                holder = new NewsCommonHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        NewsBean newsBean = list.get(position);
        switch (viewType) {
            case Constant.NEWS_IMGEXTRA:
                ImgExtraHolder imgExtraHolder = (ImgExtraHolder) holder;
                imgExtraHolder.setItemListener(mListener);
                  /**
                   *配置横向的recycle
                   * */
                if (imgExtraHolder.ry_horizontal.getAdapter()==null){
                    imgExtraHorizontalAdapter = new ImgExtraHorizontalAdapter();
                    //设置成横向
                    layoutManagerImgExtra = new LinearLayoutManager(UIUtils.getContext());
                    layoutManagerImgExtra.setOrientation(LinearLayoutManager.HORIZONTAL);
                    imgExtraHolder.ry_horizontal.setAdapter(imgExtraHorizontalAdapter);
                    imgExtraHolder.ry_horizontal.setLayoutManager(layoutManagerImgExtra);
                }
                imgExtraHorizontalAdapter.setData(newsBean);
                /***
                 * 设置title
                 * */
                imgExtraHolder.tv_title.setText(newsBean.getTitle());
                break;
            case Constant.NEWS_NORAML:
                NewsCommonHolder normalHolder = (NewsCommonHolder) holder;
                normalHolder.setItemListener(mListener);
                normalHolder.title.setText(newsBean.getTitle());
                Boolean isRead = (Boolean) spUtils.get(newsBean.getDocid(), false);
                if (isRead){
                    normalHolder.title.setTextColor(Color.GRAY);
                }else {
                    normalHolder.title.setTextColor(Color.BLACK);
                }
                String replyCount=newsBean.getReplyCount();
                if (TextUtils.isEmpty(replyCount))
                    replyCount="0";
                normalHolder.tv_replyCount.setText(replyCount + "评论  ");
                normalHolder.tv_source.setText(newsBean.getSource());
                ImageView img = normalHolder.img_head;
                ViewGroup.LayoutParams img_params = img.getLayoutParams();
                img_params.width = UIUtils.getNewsPicSize()[0];
                img_params.height = UIUtils.getNewsPicSize()[1];
                img.setLayoutParams(img_params);
                GlideUtils.loadPic(newsBean.getImgsrc(), img);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
        NewsBean bean = list.get(position);
        if (bean.getImgextra() != null) {
            return Constant.NEWS_IMGEXTRA;
        }
        return Constant.NEWS_NORAML;
    }

    /**
     * 加载头部数据
     */
    public void loadHead(List<NewsBean> new_list) {
        if (new_list == null) return;
        list.clear();
        list.addAll(new_list);
        notifyDataSetChanged();
    }

    /**
     * 加载底部数据
     */
    public void loadMore(List<NewsBean> new_list) {
        if (new_list == null) return;
        list.addAll(new_list);
        notifyDataSetChanged();
    }
    public List<NewsBean> getAdapterData() {
        return list;
    }
}
