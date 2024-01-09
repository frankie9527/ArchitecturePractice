package com.jyh.rest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jyh.rest.R;
import com.jyh.rest.base.BaseActivity;
import com.jyh.rest.base.Constant;
import com.jyh.rest.utils.spUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/3.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SHARE_MEDIA platform;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();


    }

    protected  void initViews() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void view(View view) {
        platform = null;
        if (view.getId() == R.id.tv_qq) {
            platform = SHARE_MEDIA.QQ;
        } else if (view.getId() == R.id.tv_wx) {
          //  platform = SHARE_MEDIA.WEIXIN;
        } else {

        }
        if (platform == null) return;
        UMShareAPI.get(this).getPlatformInfo(this, platform, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    Log.e("LoginActivity", entry.getKey() + "key      " + entry.getValue() + "value");
                }
                handlerData(map);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {


            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void handlerData(Map<String, String> map) {
        String name = map.get("screen_name");
        String headPath=map.get("profile_image_url");
        spUtils.put(Constant.NAME,name);
        spUtils.put(Constant.HEAD_PATH,headPath);
        spUtils.put(Constant.IS_LOGIN,true);
        Intent it = new Intent();
        it.setAction(Constant.LOGIN_BROCAST);
        sendBroadcast(it);
        finish();
    }
}
