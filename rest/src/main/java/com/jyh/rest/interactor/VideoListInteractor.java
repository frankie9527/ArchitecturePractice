package com.jyh.rest.interactor;



/**
 * Created by Administrator on 2016/9/20 0020.
 */
public interface VideoListInteractor {

    void loadVideoList(String type, int position);

    String getTpye(int current_fragment);
}
