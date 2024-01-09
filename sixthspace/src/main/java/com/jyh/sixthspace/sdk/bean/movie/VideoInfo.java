package com.jyh.sixthspace.sdk.bean.movie;

import java.io.Serializable;

/**
 * Description: 影片详情
 * Creator: yxc
 * date: 2016/9/29 9:39
 */
public class VideoInfo implements Serializable {
    public String title;
    public String pic;
    public String dataId;
    public String score;
    public String airTime;
    public String moreURL;
    public String loadType;

    public int  localPic;

    public String catalogId;

    public VideoInfo(String title, int localPic,String catalogId) {
        this.title = title;
        this.localPic = localPic;
        this.catalogId=catalogId;
    }

    public VideoInfo() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public int getLocalPic() {
        return localPic;
    }

    public void setLocalPic(int localPic) {
        this.localPic = localPic;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
