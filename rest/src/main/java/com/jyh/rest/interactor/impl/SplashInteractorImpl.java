package com.jyh.rest.interactor.impl;

import com.jyh.rest.R;
import com.jyh.rest.interactor.SplashInteractor;
import com.jyh.rest.listener.OnFinishedListener;
import com.jyh.rest.utils.ToastUtils;
import com.jyh.rest.utils.UIUtils;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SplashInteractorImpl implements SplashInteractor {



    public int getPosition(int size) {
        Random random=new Random();
        int   position=random.nextInt(size);
        return position;
    }

    @Override
    public String getCopyright() {
        return UIUtils.getString(R.string.splash_copyright);
    }

    @Override
    public String getslogan() {
        String[]  array=   UIUtils.getStringArray(R.array.slogan_array);
        int  position=getPosition(array.length);
        return array[position];
    }

    @Override
    public int getSpalshImg() {
        int [] img_array=new int[]{R.drawable.splash0,R.drawable.splash1,R.drawable.splash2};
        int  position=getPosition(img_array.length);
        return img_array[position];
    }

    @Override
    public void countSplashTime(final OnFinishedListener listener) {
       Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Long>() {
                   @Override
                   public void onCompleted() {
                       listener.onFinish();
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(Long aLong) {


                   }
               });
    }
}
