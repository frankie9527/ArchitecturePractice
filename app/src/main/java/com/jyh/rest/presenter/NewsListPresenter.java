package com.jyh.rest.presenter;
import android.app.Activity;
import com.jyh.rest.entity.NewsBean;
/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface NewsListPresenter {
    void loadingHead();
    void loadingFoot();
    void go2Activity(NewsBean bean, Activity activity);
}
