package com.jyh.rest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jyh on 2016/11/18.
 * <p>
 * 懒加载fragment
 *
 * isPrepared ：如果视图都可见了，那么这个视图一定准备好了！值一定为true
 */

public abstract class LazyFragment extends Fragment {
    private Boolean isFrist = true;
    private Boolean isPrepared = false;
    private View convertView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setFragmentView() != 0) {
            convertView = inflater.inflate(setFragmentView(), container, false);
            ButterKnife.bind(this, convertView);
            return convertView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFrist) {
                initPrepare();
            } else if (isPrepared) {
                onUserVisible();
            }
        } else {
            onUserInVisible();
        }

    }
    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
            isFrist=false;
        }else {
            isPrepared=true;
        }
    }

    public abstract int setFragmentView();

    public abstract void initViews();

    public abstract void onFirstUserVisible();

    public  void onUserVisible(){};

    public  void onUserInVisible(){};
}
