package com.jyh.rest.ui.holder;


import android.view.View;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.RecyclerViewBaseHolder;
import com.jyh.rest.customview.NoDispatchTouchRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/21 0021.
 */

public class ImgExtraHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.ry_horizontal)
    public NoDispatchTouchRecyclerView ry_horizontal;
    @BindView(R.id.tv_title)
    public TextView tv_title;

    public ImgExtraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ry_horizontal.setOnClickListener(this);
    }
}
