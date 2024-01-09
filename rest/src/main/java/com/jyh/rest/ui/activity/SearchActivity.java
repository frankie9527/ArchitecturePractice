package com.jyh.rest.ui.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
 * Created by Administrator on 2016/10/17 0017.
 */

public class SearchActivity extends BaseActivity implements ItemClickListener, ListNewsVideoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.recy_list)
    XRecyclerView recy_list;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    private CollectionOrSearchAdapter adapter;
    private LinearLayoutManager layoutManager_NewsList;
    CollectionOrSearchPresenter presenter;
    UMVideo video;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViews();

    }

    protected void initViews() {
        toolbar.inflateMenu(R.menu.search_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.search(getEditText());
                return false;
            }
        });
        layoutManager_NewsList = new LinearLayoutManager(SearchActivity.this);
        layoutManager_NewsList.setOrientation(LinearLayoutManager.VERTICAL);
        recy_list.setLayoutManager(layoutManager_NewsList);
        recy_list.setHasFixedSize(true);
        recy_list.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        adapter = new CollectionOrSearchAdapter();
        adapter.setItemListener(this);
        recy_list.setAdapter(adapter);
        presenter = new CollectionOrSearchPresenterImpl(this);
    }




    public String getEditText(){
      return  ed_search.getText().toString().trim();
  }


    @Override
    public void onloadHeadSuccess(List data) {

    }

    @Override
    public void onloadHeadError(String error) {

    }

    @Override
    public void onloadMoreSuccess(List data) {
        if (data==null||data.size()==0){
            ToastUtils.getInstance().show("没找到您想要的，╮(╯▽╰)╭！");
            return;
        }
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
    public void onItemClick(View view, int postion) {
        Collectionbean bean = adapter.getAdapterData().get(postion - 1);
        switch (view.getId()) {
            case R.id.img_share_collection:
                VideoBean videoBean = JavaBeanTransUtils.Collection2Video(bean);
                initDialog(videoBean);
                break;
            default:
                presenter.go2Activity(bean, SearchActivity.this);
                break;
        }

    }
    public void initDialog(final VideoBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
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
        video = new UMVideo(bean.getMp4_url());
        video.setThumb(new UMImage(SearchActivity.this, bean.getCover()));
        video.setTitle("闲憩");
        SHARE_MEDIA type;
        if (position == 0) {
            type = SHARE_MEDIA.QQ;
        } else {
            type = SHARE_MEDIA.QZONE;
        }
        new ShareAction(SearchActivity.this).withText(bean.getTitle())
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
}
