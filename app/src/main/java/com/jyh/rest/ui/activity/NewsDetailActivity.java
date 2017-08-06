package com.jyh.rest.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.NewsDetailBean;
import com.jyh.rest.presenter.NewsDetailPresenter;
import com.jyh.rest.presenter.impl.NewsDetailPresenterImpl;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.utils.WebUtil;
import com.jyh.rest.view.NewsDetailView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.source)
    TextView source;
    @BindView(R.id.ptime)
    TextView ptime;
    @BindView(R.id.ll_head)
    LinearLayout ll_head;
    NewsDetailPresenter presenter;
    NewsBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initViews();
        initWebSetting();
        initData();
    }

    private void initViews() {
        toolbar.inflateMenu(R.menu.new_share_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news_search:
                        initDialog();
                        break;
                    case R.id.action_news_collection:
                        presenter.collectionChoice(bean);
                        break;
                }
                return false;
            }
        });
    }

    private void initWebSetting() {
        WebSettings settings = web.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);   //与JS交互
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 80);//设置缓冲大小，我设的是8M
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCacheDir);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    hideLoading();
                }
            }
        });
    }

    public void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewsDetailActivity.this);
        String[] str = new String[]{"QQ", "QQ空间"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shareNews(which);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initData() {
        bean = (NewsBean) getIntent().getExtras().getSerializable("bean");
        presenter = new NewsDetailPresenterImpl(this, bean.getDocid());
        presenter.initialized();
    }

    /**
     * 分享到qq平台
     */
    private void shareNews(int position) {
        SHARE_MEDIA type;
        if (position == 0) {
            type = SHARE_MEDIA.QQ;
        } else {
            type = SHARE_MEDIA.QZONE;
        }
        new ShareAction(NewsDetailActivity.this).withText(bean.getTitle())
                .withTitle("闲憩")
                .withTargetUrl(bean.getUrl_3w())
                .withMedia(new UMImage(NewsDetailActivity.this, bean.getImgsrc()))
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        ToastUtils.getInstance().show(error);
    }

    @Override
    public void onLoadNewsDetailSuccess(NewsDetailBean bean) {
        String data = WebUtil.buildHtmlForIt(bean.getBody(), false);

        web.loadDataWithBaseURL(null, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, null);
        title.setText(bean.getTitle());
        source.setText(bean.getSource());
        ptime.setText(bean.getPtime());
        ll_head.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCollectionNews() {
        MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_news_collection);
        menuItem.setIcon(R.drawable.ic_collection_select);
    }

    @Override
    public void onCancelCollectionNews() {
        MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_news_collection);
        menuItem.setIcon(R.drawable.ic_collection_normal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
