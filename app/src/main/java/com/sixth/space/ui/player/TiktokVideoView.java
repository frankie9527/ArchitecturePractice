package com.sixth.space.ui.player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.sixth.space.R;
import com.sixth.space.data.dao.VideoInfo;

import org.various.player.ui.tiktok.BaseTiktokVideoView;

/**
 * author: Frankie
 * Date: 2024/3/13
 * Description:
 */
public class TiktokVideoView extends BaseTiktokVideoView {
    public ImageView image_head, image_comment, image_share;
    public TextView tv_like, tv_comment;

    public TextView tv_user_name, tv_tile;

    public TiktokVideoView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TiktokVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TiktokVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        View.inflate(context, R.layout.tiktok_view, this);
        video_container = findViewById(R.id.video_container);
        img_status = findViewById(org.various.player.R.id.img_status);
        img_status.setImageDrawable(ContextCompat.getDrawable(this.getContext(), org.various.player.R.drawable.video_play));
        image_comment = findViewById(R.id.image_comment);
        image_share = findViewById(R.id.image_share);
        img_back_ground = findViewById(R.id.img_back_ground);


        image_head = findViewById(R.id.image_head);
        tv_like = findViewById(R.id.tv_like);
        tv_comment = findViewById(R.id.tv_comment);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_tile = findViewById(R.id.tv_tile);

    }

    @SuppressLint("SetTextI18n")
    public void setVideoInfo(VideoInfo info) {
        if (info == null) {
            return;
        }
        Glide.with(this).load(info.getCover()).into(img_back_ground);
        Glide.with(this).load(info.getAvatar()).into(image_head);

        tv_user_name.setText("@" + info.getUser_name());
        tv_tile.setText(info.getDescription());
        VideoInfo.Consumption consumption = info.getConsumption();

        tv_like.setText(consumption.getCollectionCount() + "");
        tv_comment.setText(consumption.getReplyCount() + "");
    }
}
