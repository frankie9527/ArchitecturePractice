package com.jyh.rest.http;

import com.jyh.rest.entity.NewsBean;
import com.jyh.rest.entity.NewsDetailBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/20 0020.
 *public  static String NEWS_BASE="http://c.m.163.com/nc/article/";
 */
public interface NewsMethods {

    //headline/T1348647909107/0-20.html
    @GET("{type}/{id}/{position}-20.html")
   Observable<Map<String,List<NewsBean>>> getHeadlinesList(@Path("type") String type,@Path("id") String id, @Path("position") String position);


    @GET("{postId}/full.html")
    Observable<Map<String, NewsDetailBean>> getNewDetail(
            @Path("postId") String postId);


}
