package com.jyh.rest.ui.fragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.jyh.rest.R;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.ui.activity.CollectionActivity;
import com.jyh.rest.ui.activity.SearchActivity;
import com.jyh.rest.ui.adapter.NewsPagerAdapter;
import com.jyh.rest.utils.UIUtils;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/9/8 0008.
 * 新闻整体界面的fragment
 * 他是一个viewpager 里面填充的才是多个新闻列表界面
 * vp_news
 */
public class NewsPagerFragment extends LazyFragment {
    @BindView(R.id.vp_news)
    ViewPager vp_news;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    NewsPagerAdapter adapter;
    public String[] news_head_list;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(tool_bar);
        tool_bar.inflateMenu(R.menu.main_menu);
        adapter = new NewsPagerAdapter(getActivity().getSupportFragmentManager());
        vp_news.setAdapter(adapter);
        tab_layout.setupWithViewPager(vp_news);
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
        news_head_list = UIUtils.getStringArray(R.array.news_array);
        for (String name : news_head_list) {
            tab_layout.addTab(tab_layout.newTab().setText(name));
        }
        vp_news.setOffscreenPageLimit(news_head_list.length);
        adapter.setData(news_head_list);
    }
}
