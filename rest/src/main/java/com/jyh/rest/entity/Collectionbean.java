package com.jyh.rest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * Created by sunxx on 2016/11/19.
 * 收藏的javabean
 *
 * 所有网络请求的数据 的javabean 都转成这个对象 存数据库
 * 目的：为了搜索
 *
 * 字段 isCollection false 表示不收藏，反之收藏
 */
@Entity
public class Collectionbean {
    @Id
    Long id;
    /**
     * 收藏的类型
     * 1、一般新闻布局
     * 2、图片新闻布局
     * 3、视频新闻布局
     */
    int type;
    /**
     * 收藏的时间
     */
    Long time;
    /**
     * 文章的唯一性id
     */
    @Unique
    String docId;
    /**
     * 文章的标题
     */
    String title;

    /**
     * 图片地址
     */
     String imgsrc;
    /**
     * 报道媒体
     */
     String source;
    /**
     * 评论数量
     */
     String replyCount;
    /**
     * 图片集合
     * 转成json string 存起来
     * [{"img":"1"},{"img":"1"},{"img":"1"}]
     */
    String imgList;

    /**
     * 视频播放次数
     * */
    String playCount;
    /**
     * 是否收藏
     * 默认是false
     * */
    Boolean isCollection;

    /**
     * 视频播放地址
     * */
    String playUrl;

    @Generated(hash = 2067248108)
    public Collectionbean(Long id, int type, Long time, String docId, String title,
            String imgsrc, String source, String replyCount, String imgList,
            String playCount, Boolean isCollection, String playUrl) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.docId = docId;
        this.title = title;
        this.imgsrc = imgsrc;
        this.source = source;
        this.replyCount = replyCount;
        this.imgList = imgList;
        this.playCount = playCount;
        this.isCollection = isCollection;
        this.playUrl = playUrl;
    }

    @Generated(hash = 1763621681)
    public Collectionbean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return this.imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReplyCount() {
        return this.replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getImgList() {
        return this.imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getPlayCount() {
        return this.playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public Boolean getIsCollection() {
        return this.isCollection;
    }

    public void setIsCollection(Boolean isCollection) {
        this.isCollection = isCollection;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }


}
