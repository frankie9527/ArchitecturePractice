package com.jyh.rest.base;



import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.jyh.rest.listener.ItemClickListener;


/**
 * Created by jyh on 2016/9/12.
 */
public class RecyclerViewBaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    private ItemClickListener mListener=null;

    public RecyclerViewBaseHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view,  getAdapterPosition() );
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mListener != null) {
            mListener.onItemLongClick(view, getAdapterPosition());
        }
        return true;
    }

    public void setItemListener(ItemClickListener mListener){
        this.mListener = mListener;
    }


    public ItemClickListener getItemListener(){
        return  this.mListener;
    }
}
