package com.jyh.sixthspace.live.viewmodel;



import com.jyh.sixthspace.live.utils.BaseParamsMapUtil;
import com.jyh.sixthspace.sdk.bean.live.HomeCateList;
import com.jyh.sixthspace.sdk.bean.live.HttpResponse;
import com.jyh.sixthspace.sdk.http.LiveHomeMethods;
import com.jyh.sixthspace.sdk.http.NetWork;
import com.jyh.sixthspace.sdk.utlis.ToastUtils;

import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/6.
 */

public class LiveMainViewModel extends Observable {
    public void getMainData() {
        NetWork.LiveBuilder(LiveHomeMethods.class).getHomeCateList(BaseParamsMapUtil.getParamsMap()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HttpResponse<List<HomeCateList>>>() {
            @Override
            public void accept(HttpResponse<List<HomeCateList>> listHttpResponse) throws Exception {
                List<HomeCateList> list = listHttpResponse.getData();
                HomeCateList homeCateList=  new HomeCateList();
                homeCateList.setTitle("推荐");
                list.add(0,homeCateList);
                setChanged();
                notifyObservers(list);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtils.getInstance().show("getCarousel fail");
            }
        });
    }
}
