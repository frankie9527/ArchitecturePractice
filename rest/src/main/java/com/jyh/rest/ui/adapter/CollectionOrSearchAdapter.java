package com.jyh.rest.ui.adapter;

import android.graphics.Color;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jyh.rest.R;
import com.jyh.rest.base.Constant;
import com.jyh.rest.base.RecyclerViewBaseHolder;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.ui.holder.ImgExtraHolder;
import com.jyh.rest.ui.holder.NewsCommonHolder;
import com.jyh.rest.ui.holder.VideoItemHolder;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.utils.JsonUtils;
import com.jyh.rest.utils.UIUtils;
import com.jyh.rest.utils.VideoUiUtils;
import com.jyh.rest.utils.spUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */

public class CollectionOrSearchAdapter extends RecyclerView.Adapter {
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    private List<Collectionbean> collectionList = new ArrayList<>();
    private LinearLayout.LayoutParams params;
    /**
     * 横向多张图的adapter
     */
    public ImgExtraHorizontalAdapter imgExtraHorizontalAdapter;


    /**
     * 横向多张图layoutManager参数设置
     */
    private LinearLayoutManager layoutManagerImgExtra;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerViewBaseHolder holder = null;
        switch (viewType) {
            case Constant.NEWS_NORAML:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_common_item, parent, false);
                holder = new NewsCommonHolder(view);
                break;
            case Constant.NEWS_IMGEXTRA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_imgexrtra_horizontal, parent, false);
                holder = new ImgExtraHolder(view);
                break;
            case Constant.NEWS_VIDEO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video_list_item, parent, false);
                holder = new VideoItemHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Collectionbean collectionbean = collectionList.get(position);
        switch (viewType) {
            case Constant.NEWS_NORAML:
                NewsCommonHolder normalHolder = (NewsCommonHolder) holder;
                normalHolder.setItemListener(mListener);
                normalHolder.title.setText(collectionbean.getTitle());
                Boolean isRead = (Boolean) spUtils.get(collectionbean.getDocId(), false);
                if (isRead) {
                    normalHolder.title.setTextColor(Color.GRAY);
                } else {
                    normalHolder.title.setTextColor(Color.BLACK);
                }
                String replyCount = collectionbean.getReplyCount();
                if (TextUtils.isEmpty(replyCount))
                    replyCount = "0";
                normalHolder.tv_replyCount.setText(replyCount + "评论  ");
                normalHolder.tv_source.setText(collectionbean.getSource());
                ImageView img = normalHolder.img_head;
                ViewGroup.LayoutParams img_params = img.getLayoutParams();
                img_params.width = UIUtils.getNewsPicSize()[0];
                img_params.height = UIUtils.getNewsPicSize()[1];
                img.setLayoutParams(img_params);
                Glide.with(UIUtils.getContext()).load(collectionbean.getImgsrc())
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.mipmap.logo_rest)
                        .error(R.mipmap.logo_rest)
                        .into(img);
                break;
            case Constant.NEWS_IMGEXTRA:
                ImgExtraHolder imgExtraHolder = (ImgExtraHolder) holder;
                imgExtraHolder.setItemListener(mListener);
                /**
                 *配置横向的recycle
                 * */
                if (imgExtraHolder.ry_horizontal.getAdapter() == null) {
                    imgExtraHorizontalAdapter = new ImgExtraHorizontalAdapter();
                    //设置成横向
                    layoutManagerImgExtra = new LinearLayoutManager(UIUtils.getContext());
                    layoutManagerImgExtra.setOrientation(LinearLayoutManager.HORIZONTAL);
                    imgExtraHolder.ry_horizontal.setAdapter(imgExtraHorizontalAdapter);
                    imgExtraHolder.ry_horizontal.setLayoutManager(layoutManagerImgExtra);
                }
                NewsBean newsBean = null;
                try {
                    newsBean = JsonUtils.String2NewsBean(collectionbean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                imgExtraHorizontalAdapter.setData(newsBean);
                /***
                 * 设置title
                 * */
                imgExtraHolder.tv_title.setText(newsBean.getTitle());
                break;
            case Constant.NEWS_VIDEO:
                VideoItemHolder videoItemHolder = (VideoItemHolder) holder;
                if (params == null) {
                    int width = UIUtils.getWindowWidth() - UIUtils.dip2px(30);
                    int height = width / 5 * 3;
                    params = new LinearLayout.LayoutParams(width, height);
                    int num_5 = UIUtils.dip2px(5);
                    params.setMargins(num_5, num_5, num_5, 0);
                }
                videoItemHolder.rl_video_content.setLayoutParams(params);
                String str = collectionbean.getImgsrc();
                GlideUtils.loadPic(str, videoItemHolder.img_cover);
                videoItemHolder.tv_title.setText(collectionbean.getTitle());
                videoItemHolder.tv_topicName.setText(collectionbean.getSource());
                String count = VideoUiUtils.getPlayCount(collectionbean.getPlayCount());
                videoItemHolder.tv_playCount.setText(count);
                videoItemHolder.setItemListener(mListener);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return collectionList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public void setData(List<Collectionbean> list) {
        if (list == null) return;
        collectionList.addAll(list);
        notifyDataSetChanged();
    }

    public List<Collectionbean> getAdapterData() {
        return collectionList;
    }
}
