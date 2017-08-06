package com.jyh.rest.ui.fragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jyh.rest.MainActivity;
import com.jyh.rest.R;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.ui.activity.CollectionActivity;
import com.jyh.rest.ui.activity.SearchActivity;
import com.jyh.rest.ui.adapter.VideoPagerAdapter;
import com.jyh.rest.utils.UIUtils;


import butterknife.BindView;


/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class VideoPagerFragment extends LazyFragment {
    @BindView(R.id.vp_video)
    ViewPager vp_video;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    VideoPagerAdapter adapter;
    public String[] video_head_list;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_videos;
    }
    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(tool_bar);
        adapter = new VideoPagerAdapter(getActivity().getSupportFragmentManager());
        vp_video.setAdapter(adapter);
        tab_layout.setupWithViewPager(vp_video);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tool_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.action_main_search:
                        intent = new Intent(getActivity(), SearchActivity.class);
                        break;
                    case R.id.action_main_collection:
                        intent = new Intent(getActivity(), CollectionActivity.class);
                        break;
                }
                startActivity(intent);
                return false;
            }
        });
    }
    @Override
    public void onFirstUserVisible() {
        tool_bar.inflateMenu(R.menu.main_menu);
        video_head_list = UIUtils.getStringArray(R.array.video_array);
        for (String name : video_head_list) {
            tab_layout.addTab(tab_layout.newTab().setText(name));
        }
        vp_video.setOffscreenPageLimit(video_head_list.length);
        adapter.setData(video_head_list);
    }

}