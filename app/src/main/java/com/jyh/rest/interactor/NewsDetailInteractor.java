package com.jyh.rest.interactor;
import com.jyh.rest.entity.NewsBean;
/**
 * Created by Administrator on 2016/9/20 0020.
 */
public interface NewsDetailInteractor {
    void loadNewsDetail(String docid);
    /**
     * 收藏的选择
     * 如果收藏，那么久取消收藏
     * 如果没有收藏，那么则收藏
     * */
    void collectionChoice(NewsBean bean);
    /**
     * 判断是否为收藏
     * */
    void isCollectionChoice(String docid);
}
