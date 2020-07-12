package com.jyh.rest.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jyh.rest.ui.fragment.VideoListFragment;


/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class VideoPagerAdapter extends FragmentStatePagerAdapter {
 private    String [] video_head_list;
    public VideoPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return VideoListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        if (video_head_list==null)return  0;
       return video_head_list.length;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return video_head_list[position];
    }

    public  void setData(String [] news_head_list){
       this.video_head_list=news_head_list;
       notifyDataSetChanged();

   }
}
