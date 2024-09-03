package com.sixth.space.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.various.player.ui.tiktok.BaseTiktokVideoView;

public class HomeVideoView extends BaseTiktokVideoView {
    public HomeVideoView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public HomeVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        View.inflate(context, org.various.player.R.layout.various_tiktok_view, this);
        video_container = findViewById(org.various.player.R.id.video_container);
        img_status=findViewById(org.various.player.R.id.img_status);
        img_back_ground=findViewById(org.various.player.R.id.img_back_ground);
    }
}
