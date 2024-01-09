package com.jyh.rest.interactor.impl;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.interactor.MainInteractor;
import com.jyh.rest.ui.fragment.MineFragment;
import com.jyh.rest.ui.fragment.NewsPagerFragment;
import com.jyh.rest.ui.fragment.VideoPagerFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class MainInteractorImpl implements MainInteractor {
    @Override
    public List<LazyFragment> getTabFragment() {
        List<LazyFragment> tab_list = new ArrayList<>();
        tab_list.add(new NewsPagerFragment());
        tab_list.add(new VideoPagerFragment());
        tab_list.add(new MineFragment());
        return tab_list;
    }
}
