package com.jyh.rest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jyh.rest.R;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.ui.holder.ImgExtraHorizontalHolder;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.utils.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 * <p>
 * <p>
 * 横向的 多个图片的适配器
 * <p>
 * 在新闻布局里面也许有多个图片
 * 这些图片是横向的 所以写了这个横向的适配器
 */

public class ImgExtraHorizontalAdapter extends RecyclerView.Adapter {
    private List<NewsBean.Imgextra> list;
    private String title;

    private ImgExtraHorizontalHolder horizontalHolder;
    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imgextra_horizontal_item, parent, false);
        horizontalHolder = new ImgExtraHorizontalHolder(view);
        return horizontalHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ImgExtraHorizontalHolder horizontalHolder = (ImgExtraHorizontalHolder) holder;
        String str = list.get(position).getImgsrc();
        ImageView img = horizontalHolder.img_extra;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = UIUtils.getNewsPicSize()[0];
        params.height = UIUtils.getNewsPicSize()[1];
        img.setLayoutParams(params);
        img.setPadding(0, 0, UIUtils.dip2px(5), 0);

        GlideUtils.loadPic(str, img);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public void setData(NewsBean newsBean) {
        this.list = newsBean.getImgextra();
        this.title = newsBean.getTitle();
        notifyDataSetChanged();

    }
}
