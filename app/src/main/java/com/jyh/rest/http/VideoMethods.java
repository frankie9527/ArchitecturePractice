package com.jyh.rest.http;




import com.jyh.rest.entity.VideoBean;

import java.util.List;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface VideoMethods {

   //V9LG4B3A0/n/0-10.html
    @GET("{type}/n/{position}-10.html")
    Observable<Map<String,List<VideoBean>>> getVideoList(@Path("type") String type, @Path("position") String position);


}
