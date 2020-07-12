package com.jyh.rest.ui.adapter;



import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.jyh.rest.R;
import com.jyh.rest.base.RecyclerViewBaseHolder;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.ui.holder.VideoItemHolder;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.utils.UIUtils;
import com.jyh.rest.utils.VideoUiUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class VideoListRecyclerAdapter extends RecyclerView.Adapter {
    private List<VideoBean> list = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    private LinearLayout.LayoutParams params;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video_list_item, parent, false);
        RecyclerViewBaseHolder holder = new VideoItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoBean videoBean = list.get(position);
        VideoItemHolder videoItemHolder = (VideoItemHolder) holder;
        if (params == null) {
            int width = UIUtils.getWindowWidth() - UIUtils.dip2px(30);
            int height = width / 5 * 3;
            params = new LinearLayout.LayoutParams(width, height);
            int num_5 = UIUtils.dip2px(5);
            params.setMargins(num_5, num_5, num_5, 0);
        }
        videoItemHolder.rl_video_content.setLayoutParams(params);
        String str = videoBean.getCover();
        GlideUtils.loadPic(str, videoItemHolder.img_cover);
        videoItemHolder.tv_title.setText(videoBean.getTitle());
        if (TextUtils.isEmpty(videoBean.getTopicName())) {
            videoItemHolder.tv_topicName.setText(videoBean.getSectiontitle());
        } else {
            videoItemHolder.tv_topicName.setText(videoBean.getTopicName());
        }
        String count = VideoUiUtils.getPlayCount(videoBean.getPlayCount());
        videoItemHolder.tv_playCount.setText(count);
        videoItemHolder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * 加载头部数据
     */
    public void loadHead(List<VideoBean> new_list) {
        if (new_list == null) return;
        list.clear();
        list.addAll(new_list);
        notifyDataSetChanged();
    }

    /**
     * 加载底部数据
     */
    public void loadMore(List<VideoBean> new_list) {
        if (new_list == null) return;
        list.addAll(new_list);
        notifyDataSetChanged();
    }


    public List<VideoBean> getAdapterData() {
        return list;
    }
}
