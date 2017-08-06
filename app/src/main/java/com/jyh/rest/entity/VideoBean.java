package com.jyh.rest.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/3.
 */

public class VideoBean implements Serializable{
    /**
     * 视频图片地址
     * */
    String cover;


    /**
     * 视频的标题
     * */
    String title;
    /**
     * 视频播放次数
     * */
    String playCount;
    /**
     * m3u8 标清地址
     * */
    String m3u8_url;
    /**
     * m3u8高清地址
     * */
    String m3u8Hd_url;
    /**
     * mp4地址
     * */
    String mp4_url;
    /**
     * 视频时长
     * */
    String length;



    /**
     * 谁发布的
     * */
    String topicName;



    String sectiontitle;
    public String getSectiontitle() {
        return sectiontitle;
    }
    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getTopicName() {
        return topicName;
    }
    public String getPlayCount() {
        return playCount;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public String getLength() {
        return length;
    }


    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }
}
