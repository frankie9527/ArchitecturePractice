package com.jyh.sixthspace.live.viewmodel;

import android.util.Log;

import com.google.gson.Gson;
import com.jyh.sixthspace.sdk.bean.live.TempLiveVideoInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/10/10.
 */

public class LiveDanMuModel extends Observable {
    public  void getLiveUrl(String Room_id){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        String str = "https://m.douyu.com/html5/live?roomId=" + Room_id;

        Request requestPost = new Request.Builder()
//                .url(NetWorkApi.oldBaseUrl+ NetWorkApi.getOldLiveVideo+ room_id + "?rate=0")
                .url(str)
                .get()
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("error", e.getMessage() + "---");

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String json = response.body().string().toString();
                Log.e("onResponse", json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("error") == 0) {
                        Gson gson = new Gson();
                        TempLiveVideoInfo mLiveVideoInfo = gson.fromJson(json, TempLiveVideoInfo.class);
                        setChanged();
                       notifyObservers(mLiveVideoInfo.getData().getHls_url());
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
