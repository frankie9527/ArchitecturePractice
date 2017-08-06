package com.jyh.rest.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class NewsCommonHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.tv_replyCount)
    public TextView tv_replyCount;
    @BindView(R.id.tv_source)
    public TextView tv_source;
    @BindView(R.id.img_head)
    public ImageView img_head;
    public NewsCommonHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
