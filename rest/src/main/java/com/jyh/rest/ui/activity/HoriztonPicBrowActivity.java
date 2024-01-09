package com.jyh.rest.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.presenter.HoriztonPicBrowPresenter;
import com.jyh.rest.presenter.impl.HoriztionPicBrowPresenterImpl;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.utils.StatusBarCompat;
import com.jyh.rest.view.HoriztonPicBrowlView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class HoriztonPicBrowActivity extends BaseActivity implements HoriztonPicBrowlView {
    @BindView(R.id.id_viewpager)
    ViewPager id_viewpager;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<NewsBean.Imgextra> list;
    private ImageView[] mImageViews;
    private String title;
    HoriztonPicBrowPresenter picBrowPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horiztion_picbrow);
        ButterKnife.bind(this);
        initViews();
        initData();

    }




    protected  void initData() {
        NewsBean bean = (NewsBean) getIntent().getExtras().getSerializable("bean");
        picBrowPresenter = new HoriztionPicBrowPresenterImpl(this, bean);
        picBrowPresenter.initialized();
        list = bean.getImgextra();
        title = bean.getTitle();
        mImageViews = new ImageView[list.size()];
        id_viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if (list==null)return 0;
                return list.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = new PhotoView(
                        getApplicationContext());
                GlideUtils.loadPicBrow(list.get(position).getImgsrc(), photoView);
                container.addView(photoView);
                mImageViews[position] = photoView;
                return photoView;
            }
        });
        id_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String str = (position + 1) + "/" + list.size() + "    " + title;
                tv_detail.setText(str);
            }

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    protected  void initViews() {
       StatusBarCompat.compat(HoriztonPicBrowActivity.this, Color.BLACK);
       // setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.pic_brow_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                picBrowPresenter.collectionChoice();
                return false;
            }
        });

    }

    @Override
    public void onCollectionNews() {
        MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_pic_collection);
        menuItem.setIcon(R.drawable.ic_collection_select);

    }

    @Override
    public void onCancelCollectionNews() {
        MenuItem menuItem = toolbar.getMenu().findItem(R.id.action_pic_collection);
        menuItem.setIcon(R.drawable.ic_collection_normal);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
