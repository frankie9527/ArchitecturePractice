package com.jyh.rest.utils;

import android.text.TextUtils;

import com.jyh.rest.base.Constant;
import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.VideoBean;

import org.json.JSONException;

/**
 * Created by Administrator on 2016/12/5.
 *
 * java bean 转换类
 * 把新闻的javabean 或者视频的javabean 转化为收藏的javabean
 */

public class JavaBeanTransUtils {
    /**
     * 新闻的一般布局bean 转化为 收藏的javabean
     * */
    public static Collectionbean newsNoraml2Bean(NewsBean bean){
        Collectionbean collectionbean = new Collectionbean();
        collectionbean.setType(Constant.NEWS_NORAML);
        collectionbean.setDocId(bean.getDocid());
        collectionbean.setTitle(bean.getTitle());
        collectionbean.setSource(bean.getSource());
        collectionbean.setReplyCount(bean.getReplyCount());
        collectionbean.setImgsrc(bean.getImgsrc());
        collectionbean.setTime(System.currentTimeMillis());
        return collectionbean;
    }
    /**
     * 新闻的多图片布局bean 转化为 收藏的javabean
     * */
    public  static  Collectionbean newsImgextra2Bean(NewsBean bean){
        Collectionbean collectionbean = new Collectionbean();
        collectionbean.setType(Constant.NEWS_IMGEXTRA);
        collectionbean.setDocId(bean.getDocid());
        collectionbean.setTitle(bean.getTitle());
        String imglist=JsonUtils.Imgextra2String(bean.getImgextra());
        collectionbean.setImgList(imglist);
        collectionbean.setTime(System.currentTimeMillis());
        return collectionbean;
    }
    public  static  Collectionbean Video2Bean(VideoBean bean){
        Collectionbean collectionbean = new Collectionbean();
        collectionbean.setType(Constant.NEWS_VIDEO);
        collectionbean.setDocId(bean.getMp4_url());
        collectionbean.setTitle(bean.getTitle());
        collectionbean.setImgsrc(bean.getCover());
        collectionbean.setPlayCount(bean.getPlayCount());
        collectionbean.setPlayUrl(bean.getMp4_url());
        if (TextUtils.isEmpty(bean.getTopicName())) {
            collectionbean.setSource(bean.getSectiontitle());
        } else {
            collectionbean.setSource(bean.getTopicName());
        }

        collectionbean.setTime(System.currentTimeMillis());
     return collectionbean;
    }
    public  static  NewsBean Collection2NewNormal(Collectionbean collectionbean){
        NewsBean bean=new NewsBean();
        bean.setDocid(collectionbean.getDocId());
        bean.setTitle(collectionbean.getTitle());
        bean.setSource(collectionbean.getSource());
        bean.setReplyCount(collectionbean.getReplyCount());
        bean.setImgsrc(collectionbean.getImgsrc());
        return bean;
    }
    public  static NewsBean Collection2Imgextra(Collectionbean collectionbean) throws JSONException {
        NewsBean bean=JsonUtils.String2NewsBean(collectionbean);
        return  bean;
    }
    public  static VideoBean Collection2Video(Collectionbean collectionbean){
        VideoBean bean=new VideoBean();
        bean.setTitle(collectionbean.getTitle());
        bean.setCover(collectionbean.getImgsrc());
        bean.setPlayCount(collectionbean.getPlayCount());
        bean.setMp4_url(collectionbean.getPlayUrl());
        bean.setTopicName(collectionbean.getSource());
        return  bean;
    }
}
