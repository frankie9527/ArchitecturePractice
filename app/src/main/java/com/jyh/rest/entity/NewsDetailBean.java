package com.jyh.rest.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/4 0004.
 */

public class NewsDetailBean {
    /**
     * 新闻内容
     */
    private String body;
    /**
     * 新闻内容里面的图片集合
     */
    private List<NewsDetailImg> img;
    /**
     * 新闻里面的标题
     * */
    private String title;
    /**
     * 那个媒体报道的
     * */
    private String source;
    /**
     *发表时间
     * */
    private String ptime;
    /**
     *分享链接
     * */
    private String shareLink;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<NewsDetailImg> getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getPtime() {
        return ptime;
    }

    public String getShareLink() {
        return shareLink;
    }


    /**
     * 新闻里面图片的对象
     * */
    public class NewsDetailImg {
        private String ref;
        private String pixel;
        private String src;



        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }


        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }
}
