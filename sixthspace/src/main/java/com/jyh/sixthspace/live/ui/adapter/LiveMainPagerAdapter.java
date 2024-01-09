package com.jyh.sixthspace.live.ui.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jyh.sixthspace.live.ui.fragment.LiveCommonFragment;
import com.jyh.sixthspace.live.ui.fragment.LiveReCommedFrament;
import com.jyh.sixthspace.sdk.bean.live.HomeCateList;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class LiveMainPagerAdapter extends FragmentStatePagerAdapter {
    List<HomeCateList> list;

    public LiveMainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LiveReCommedFrament();
        }
        return LiveCommonFragment.newInstance(position);
    }

    @Override
    public int getCount() {
//        if (list == null)
//            return 0;
//        return list.size();
        return 1;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return list.get(position).getTitle();
//    }

    public void setData(List<HomeCateList> list) {
        this.list = list;
        notifyDataSetChanged();

    }
}
