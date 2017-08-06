package com.jyh.rest.view;

import com.jyh.rest.base.BaseResponseView;

/**
 * Created by Administrator on 2016/11/10.
 */

public interface SplashView extends BaseResponseView {
   void  go2Main();
   void  showSplashImg(int id);
   void  showCopyRight(String copyRight);
   void  showSlogan(String slogan);
}
