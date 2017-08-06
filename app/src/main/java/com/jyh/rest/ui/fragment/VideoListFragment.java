package com.jyh.rest.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jyh.rest.R;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.customview.ProgressWheel;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.CollectionbeanDao;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.presenter.VideoListPresenter;
import com.jyh.rest.presenter.impl.VideoListPresenterImpl;
import com.jyh.rest.ui.activity.VideoPlayActivity;
import com.jyh.rest.ui.adapter.VideoListRecyclerAdapter;
import com.jyh.rest.utils.GreenDaoMananger;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.utils.UIUtils;
import com.jyh.rest.view.ListNewsVideoView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/9/10 0010.
 * 眼睛所看到的新闻列表界面
 * 显示的是新闻列表集合
 * 点击它的item可以跳入新闻详情界面
 */
public class VideoListFragment extends LazyFragment implements ItemClickListener, ListNewsVideoView {
    @BindView(R.id.recy_list)
    XRecyclerView recy_list;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    private int current_position;
    private VideoListPresenter presenter;
    private VideoListRecyclerAdapter videoListRecyclerAdapter;
    private LinearLayoutManager layoutManager_NewsList;
    UMVideo video;
    public static VideoListFragment newInstance(int position) {
        Bundle args = new Bundle();
        VideoListFragment fragment = new VideoListFragment();
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
        videoListRecyclerAdapter = new VideoListRecyclerAdapter();
        recy_list.setAdapter(videoListRecyclerAdapter);
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
        videoListRecyclerAdapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        current_position = getArguments().getInt("position");
        presenter = new VideoListPresenterImpl(this, current_position);
        presenter.loadingHead();

    }


    @Override
    public void onItemClick(View view, int postion) {
        VideoBean bean = videoListRecyclerAdapter.getAdapterData().get(postion - 1);
        switch (view.getId()) {
            case R.id.img_share_collection:
                initDialog(bean);
                break;
            default:
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean);
                intent.putExtras(bundle);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getActivity(), 0, 0);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                break;
        }

    }


    public void initDialog(final VideoBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] str = new String[]{"QQ", "QQ空间", "收藏"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                    case 1:
                        shareNews(which, bean);
                        break;
                    default:
                        CollectionVideo(bean);
                        break;
                }

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 收藏的方法
     */

    public void CollectionVideo(VideoBean bean) {
        CollectionbeanDao dao = (CollectionbeanDao) GreenDaoMananger.getInstance(UIUtils.getContext()).getDao(Collectionbean.class);
        Collectionbean collectionbean = JavaBeanTransUtils.Video2Bean(bean);
        collectionbean.setIsCollection(true);
        dao.insertOrReplace(collectionbean);
        ToastUtils.getInstance().show("收藏成功(｡･∀･)ﾉﾞ");
    }

    /**
     * 分享到qq平台
     */
    private void shareNews(int position, VideoBean bean) {
        video=new UMVideo(bean.getMp4_url());
        video.setThumb(new UMImage(getActivity(),bean.getCover()));
        video.setTitle("闲憩");
        SHARE_MEDIA type;
        if (position == 0) {
            type = SHARE_MEDIA.QQ;
        } else {
            type = SHARE_MEDIA.QZONE;
        }
        new ShareAction(getActivity()).withText(bean.getTitle())
                .withTitle("闲憩")
                .withMedia(video)
                .setPlatform(type)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                    }
                })
                .share();
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
        videoListRecyclerAdapter.loadHead(data);
        progress_bar.setVisibility(View.GONE);

    }

    @Override
    public void onloadMoreSuccess(List data) {
        recy_list.loadMoreComplete();
        videoListRecyclerAdapter.loadMore(data);

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
