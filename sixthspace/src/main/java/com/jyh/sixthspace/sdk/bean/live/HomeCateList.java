package com.jyh.sixthspace.sdk.bean.live;


import java.io.Serializable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：   首页列表
 *  备注消息：
 *  修改时间：2016/12/14 下午6:14
 **/

public class HomeCateList  implements Serializable{

    /**
     * title : 手游
     * show_order : 1
     * identification : 3e760da75be261a588c74c4830632360
     * is_video : 0
     */

    private String title;
    private String show_order;
    private String identification;
    private int is_video;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShow_order() {
        return show_order;
    }

    public void setShow_order(String show_order) {
        this.show_order = show_order;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getIs_video() {
        return is_video;
    }

    public void setIs_video(int is_video) {
        this.is_video = is_video;
    }

    @Override
    public String toString() {
        return "{" +
                "title:'" + title + '\'' +
                ", show_order:'" + show_order + '\'' +
                ", identification:'" + identification + '\'' +
                ", is_video:" + is_video +
                '}';
    }
}
