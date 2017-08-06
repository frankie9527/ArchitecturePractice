package com.jyh.rest.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/12/7.
 */

public class NoDispatchTouchRecyclerView extends RecyclerView {
    public NoDispatchTouchRecyclerView(Context context) {
        super(context);
    }

    public NoDispatchTouchRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoDispatchTouchRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 拦截
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }


}
