package com.jyh.rest.presenter;


import com.jyh.rest.entity.NewsBean;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface NewsDetailPresenter {
     /**
      * 加载新闻
      * 判断是否收藏
      * */
     void initialized();
     /**
      * 收藏选择的判断
      * 如果收藏了，点击则取消
      * 如果没收藏，点击则收藏
      * */
     void collectionChoice(NewsBean bean);
}
