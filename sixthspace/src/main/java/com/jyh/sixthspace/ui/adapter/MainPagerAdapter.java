package com.jyh.sixthspace.ui.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jyh.sixthspace.model.MainFragmentFactory;
/**
 * Created by Administrator on 2016/9/7 0007.
 *
 * 主界面viewpager的适配器
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return MainFragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
