package com.jyh.rest.ui.fragment;

import android.os.Bundle;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jyh.rest.R;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.customview.ProgressWheel;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.presenter.NewsListPresenter;
import com.jyh.rest.presenter.impl.NewsListPresenterImpl;
import com.jyh.rest.ui.adapter.NewsListRecyclerAdapter;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.view.ListNewsVideoView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/9/10 0010.
 * 眼睛所看到的新闻列表界面
 * 显示的是新闻列表集合
 * 点击它的item可以跳入新闻详情界面
 */
public class NewsListFragment extends LazyFragment implements ItemClickListener, ListNewsVideoView {
    @BindView(R.id.recy_list)
    XRecyclerView recy_list;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    private int current_position;
    private NewsListPresenter presenter;
    private NewsListRecyclerAdapter newsListRecyclerAdapter;
    private LinearLayoutManager layoutManager_NewsList;

    public static NewsListFragment newInstance(int position) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_list;
    }

    @Override
    public void initViews() {
        layoutManager_NewsList = new LinearLayoutManager(getContext());
        layoutManager_NewsList.setOrientation(LinearLayoutManager.VERTICAL);
        recy_list.setLayoutManager(layoutManager_NewsList);
        recy_list.setHasFixedSize(true);
        recy_list.setRefreshProgressStyle(ProgressStyle.SysProgress);
        recy_list.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        newsListRecyclerAdapter = new NewsListRecyclerAdapter();
        recy_list.setAdapter(newsListRecyclerAdapter);
        recy_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.loadingHead();
            }

            @Override
            public void onLoadMore() {
                presenter.loadingFoot();
            }
        });
        newsListRecyclerAdapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        current_position = getArguments().getInt("position");
        presenter = new NewsListPresenterImpl(this, current_position);
        presenter.loadingHead();

    }


    @Override
    public void onItemClick(View view, int postion) {
        NewsBean bean = newsListRecyclerAdapter.getAdapterData().get(postion - 1);
        presenter.go2Activity(bean, getActivity());
        newsListRecyclerAdapter.notifyItemChanged(postion);
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }


    @Override
    public void showLoading() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress_bar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onloadHeadSuccess(List data) {
        recy_list.refreshComplete();
        newsListRecyclerAdapter.loadHead(data);
        progress_bar.setVisibility(View.GONE);

    }

    @Override
    public void onloadMoreSuccess(List data) {
        recy_list.loadMoreComplete();
        newsListRecyclerAdapter.loadMore(data);

    }

    @Override
    public void onloadHeadError(String error) {
        recy_list.refreshComplete();
        recy_list.loadMoreComplete();
        if (progress_bar.getVisibility() == View.VISIBLE)
            progress_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        ToastUtils.getInstance().show(error);
        recy_list.refreshComplete();
        recy_list.loadMoreComplete();
        if (progress_bar.getVisibility() == View.VISIBLE)
            progress_bar.setVisibility(View.INVISIBLE);
    }

}
