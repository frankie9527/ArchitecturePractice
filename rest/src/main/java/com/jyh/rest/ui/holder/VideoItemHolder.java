package com.jyh.rest.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class VideoItemHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_share_collection)
    public ImageView img_share_collection;
    @BindView(R.id.img_cover)
    public ImageView img_cover;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_topicName)
    public TextView tv_topicName;
    @BindView(R.id.tv_playCount)
    public TextView tv_playCount;
    @BindView(R.id.rl_video_content)
    public RelativeLayout rl_video_content;
    public VideoItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        img_share_collection.setOnClickListener(this);
    }
}
