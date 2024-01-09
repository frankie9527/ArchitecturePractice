package com.jyh.sixthspace.live.ui.fragment;



import androidx.appcompat.app.AppCompatActivity;

import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.FragmentLiveMainBinding;
import com.jyh.sixthspace.live.ui.adapter.LiveMainPagerAdapter;
import com.jyh.sixthspace.live.viewmodel.LiveMainViewModel;

import com.jyh.sixthspace.sdk.bean.live.HomeCateList;
import com.jyh.sixthspace.uilibrary.base.LazyFragment;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Administrator on 2017/4/13.
 */

public class LiveMainFragment extends LazyFragment<FragmentLiveMainBinding> implements Observer {
    LiveMainPagerAdapter adapter;
    LiveMainViewModel mainViewModel;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_live_main;
    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(jyhBinding.toolBar);
        adapter = new LiveMainPagerAdapter(getChildFragmentManager());
        jyhBinding.vpMain.setAdapter(adapter);
        jyhBinding.tabLayout.setupWithViewPager(jyhBinding.vpMain);
    }

    @Override
    public void onFirstUserVisible() {
        mainViewModel = new LiveMainViewModel();
        mainViewModel.addObserver(this);
        mainViewModel.getMainData();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof LiveMainViewModel) {
            LiveMainViewModel recommendViewModel = (LiveMainViewModel) observable;
            List<HomeCateList> list = (List<HomeCateList>) o;
            for (int i = 0; i < list.size(); i++) {
                String title = list.get(i).getTitle();
                jyhBinding.tabLayout.addTab(jyhBinding.tabLayout.newTab().setText(title));
            }
            jyhBinding.vpMain.setOffscreenPageLimit(list.size());
            adapter.setData(list);
        }

    }
}
