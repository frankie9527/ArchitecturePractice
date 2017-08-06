package com.jyh.rest.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.customview.ProgressWheel;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.VideoBean;
import com.jyh.rest.listener.ItemClickListener;
import com.jyh.rest.presenter.CollectionOrSearchPresenter;
import com.jyh.rest.presenter.impl.CollectionOrSearchPresenterImpl;
import com.jyh.rest.ui.adapter.CollectionOrSearchAdapter;
import com.jyh.rest.utils.JavaBeanTransUtils;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.view.ListNewsVideoView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/4.
 */

public class CollectionActivity extends BaseActivity implements ItemClickListener, ListNewsVideoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recy_list)
    XRecyclerView recy_list;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    UMVideo video;
    private CollectionOrSearchAdapter adapter;
    private LinearLayoutManager layoutManager_NewsList;

    CollectionOrSearchPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        initViews();


    }




    protected void initViews() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager_NewsList = new LinearLayoutManager(CollectionActivity.this);
        layoutManager_NewsList.setOrientation(LinearLayoutManager.VERTICAL);
        recy_list.setLayoutManager(layoutManager_NewsList);
        recy_list.setHasFixedSize(true);
        recy_list.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        adapter = new CollectionOrSearchAdapter();
        adapter.setItemListener(this);
        recy_list.setAdapter(adapter);
        presenter = new CollectionOrSearchPresenterImpl(this);
        presenter.load();
    }
    @Override
    public void onItemClick(View view, int postion) {
        Collectionbean bean = adapter.getAdapterData().get(postion - 1);
        switch (view.getId()) {
            case R.id.img_share_collection:
                VideoBean videoBean = JavaBeanTransUtils.Collection2Video(bean);
                initDialog(videoBean);
                break;
            default:
                presenter.go2Activity(bean, CollectionActivity.this);
                break;
        }

    }
    public void initDialog(final VideoBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CollectionActivity.this);
        String[] str = new String[]{"QQ", "QQ空间"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                    case 1:
                        shareNews(which, bean);
                        break;
                    default:

                        break;
                }

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 分享到qq平台
     */
    private void shareNews(int position, VideoBean bean) {
        video = new UMVideo("http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
        video.setThumb(new UMImage(CollectionActivity.this, bean.getCover()));
        video.setTitle("闲憩");
        SHARE_MEDIA type;
        if (position == 0) {
            type = SHARE_MEDIA.QQ;
        } else if (position==1){
            type = SHARE_MEDIA.QZONE;
        }else if (position==2){
            type=SHARE_MEDIA.WEIXIN;
        }else {
            type=SHARE_MEDIA.WEIXIN_CIRCLE;
        }
        new ShareAction(CollectionActivity.this).withText(bean.getTitle())
                .withTitle("闲憩")
                .withMedia(video)
                .setPlatform(type)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        ToastUtils.getInstance().show("分享失败");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                    }
                })
                .share();
    }

    @Override
    public void onloadHeadSuccess(List data) {

    }

    @Override
    public void onloadHeadError(String error) {

    }

    @Override
    public void onloadMoreSuccess(List data) {
        adapter.setData(data);
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
    public void showError(String error) {
        ToastUtils.getInstance().show(error);
    }



    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
