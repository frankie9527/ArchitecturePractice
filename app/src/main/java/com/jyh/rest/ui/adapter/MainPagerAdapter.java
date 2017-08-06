package com.jyh.rest.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jyh.rest.base.LazyFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 *
 * 主界面viewpager的适配器
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    List<LazyFragment> list;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list==null)return 0;
        return list.size();
    }
    public  void  setData( List<LazyFragment> list){
        this.list=list;
        notifyDataSetChanged();
    }
}
