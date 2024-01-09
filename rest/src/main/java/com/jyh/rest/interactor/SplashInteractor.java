package com.jyh.rest.interactor;

import com.jyh.rest.listener.OnFinishedListener;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public interface SplashInteractor {
      String getCopyright();
      String getslogan();
      int getSpalshImg();

      void countSplashTime(OnFinishedListener listener);

}
