package com.jyh.rest.entity;

import java.io.Serializable;
import java.util.List;


public class NewsBean implements Serializable {

    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String imgsrc;
    /**
     * 报道媒体
     */
    private String source;

    /**
     * 多图的布局
     */
    private List<Imgextra> imgextra;

    /**
     * 评论数量
     */
    private String replyCount;


    /**
     * 新闻详情的html
     */
    private String url_3w;




    private String docid;

    public String getDocid() {
        return docid;
    }
    public String getUrl_3w() {
        return url_3w;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public String getTitle() {
        return title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getSource() {
        return source;
    }



    public List<Imgextra> getImgextra() {
        return imgextra;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImgextra(List<Imgextra> imgextra) {
        this.imgextra = imgextra;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }


    public static class Imgextra implements Serializable{
        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
