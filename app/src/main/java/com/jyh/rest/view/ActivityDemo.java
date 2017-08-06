package com.jyh.rest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jyh.rest.R;
import com.jyh.rest.entity.ImgBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 *
 */

public class ActivityDemo extends AppCompatActivity {
    List<ImgBean> list;
    String json;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        list=new ArrayList<>();
        list.add(new ImgBean("url1"));
        list.add(new ImgBean("url2"));
        list.add(new ImgBean("url3"));
    }
    public  void tojson(View view) throws JSONException {
        JSONArray array=new JSONArray();
        for (int i=0;i<list.size();i++){
            array.put(i,"img:"+list.get(i));
        }
        json=array.toString();
        Log.e("tojson",json);
    }
    public void tobean(View view) throws JSONException {
        JSONArray  array=new JSONArray(json);
        for (int i=0;i<array.length();i++){
            ImgBean index= (ImgBean) array.get(0);
            Log.e("tobean",index.getImg());
        }
    }
}
