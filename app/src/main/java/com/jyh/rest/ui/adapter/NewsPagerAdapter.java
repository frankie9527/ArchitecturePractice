package com.jyh.rest.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jyh.rest.ui.fragment.NewsListFragment;


/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class NewsPagerAdapter  extends FragmentStatePagerAdapter {
 private    String [] news_head_list;
    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        if (news_head_list==null)return  0;
       return news_head_list.length;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return news_head_list[position];
    }

    public  void setData(String [] news_head_list){
       this.news_head_list=news_head_list;
       notifyDataSetChanged();

   }
}
