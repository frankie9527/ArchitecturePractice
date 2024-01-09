package com.jyh.rest.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyh.rest.R;
import com.jyh.rest.base.Constant;
import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.entity.MyinfosBean;
import com.jyh.rest.presenter.MineFragmentPresenter;
import com.jyh.rest.presenter.impl.MineFragmentPresenterImpl;
import com.jyh.rest.ui.activity.AboutActivity;
import com.jyh.rest.ui.activity.CollectionActivity;
import com.jyh.rest.ui.activity.SearchActivity;
import com.jyh.rest.utils.GlideUtils;
import com.jyh.rest.view.MineFragmentView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/11/14.
 */

public class MineFragment extends LazyFragment implements MineFragmentView {
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.tv_name)
    TextView tv_name;

    MineFragmentPresenter presenter;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        /**
         * 注册广播
         * */
        BroadcastReceiver loginBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                presenter.initialized();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.LOGIN_BROCAST);
        getActivity().registerReceiver(loginBroadcastReceiver, intentFilter);
    }

    @OnClick(R.id.img_head)
    public void ImgHeadClick() {
        presenter.login(getActivity());
    }


    @Override
    public void onFirstUserVisible() {
        presenter = new MineFragmentPresenterImpl(this, getActivity());
        presenter.initialized();
    }


    @Override
    public void loadMyinfosSuccess(MyinfosBean bean) {
        GlideUtils.loadCircular(img_head, bean.getImg_url());
        if (TextUtils.isEmpty(bean.getName())) {
            tv_name.setText("点击登录");
            tv_name.setTextColor(Color.GRAY);
        } else {
            tv_name.setText(bean.getName());
            tv_name.setTextColor(Color.BLACK);
        }
    }

    @OnClick(R.id.rl_collection)
    public  void go2Collection(){
        Intent intent=new Intent(getActivity(),CollectionActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.rl_search)
    public  void go2Search(){
        Intent intent=new Intent(getActivity(),SearchActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.rl_about)
    public  void go2About(){
        Intent intent=new Intent(getActivity(),AboutActivity.class);
        startActivity(intent);
    }
}

