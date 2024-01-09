package com.jyh.rest.utils;

import com.jyh.rest.entity.Collectionbean;
import com.jyh.rest.entity.NewsBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */

public class JsonUtils {
    public static String Imgextra2String(List<NewsBean.Imgextra> list){
        JSONArray array = new JSONArray();
        for (NewsBean.Imgextra img : list) {
            array.put(img.getImgsrc());
        }
        return array.toString();
    }

    public static NewsBean String2NewsBean(Collectionbean collectionbean) throws JSONException {
        NewsBean newsBean = new NewsBean();
        newsBean.setTitle(collectionbean.getTitle());
        JSONArray array = new JSONArray(collectionbean.getImgList());
        List<NewsBean.Imgextra> imgextraList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            NewsBean.Imgextra imgextra1 = new NewsBean.Imgextra();
            String url = array.get(i).toString();
            imgextra1.setImgsrc(url);
            imgextraList.add(imgextra1);
        }
        newsBean.setTitle(collectionbean.getTitle());
        newsBean.setDocid(collectionbean.getDocId());
        newsBean.setImgextra(imgextraList);
        return newsBean;
    }
}
