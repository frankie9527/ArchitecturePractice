package com.jyh.sixthspace.live.viewmodel;

import com.jyh.sixthspace.live.utils.BaseParamsMapUtil;
import com.jyh.sixthspace.live.utils.ParamsMapUtils;
import com.jyh.sixthspace.live.view.LiveReCommedView;
import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.bean.live.HomeFaceScoreColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;
import com.jyh.sixthspace.sdk.bean.live.HttpResponse;
import com.jyh.sixthspace.sdk.http.LiveHomeMethods;
import com.jyh.sixthspace.sdk.http.NetWork;
import com.jyh.sixthspace.sdk.utlis.CrashHandler;
import com.jyh.sixthspace.sdk.utlis.ToastUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/5.
 */

public class LiveReCommedViewModel {
    LiveReCommedView view;

    public LiveReCommedViewModel(LiveReCommedView view) {
        this.view = view;
        initCarousel();
        initHot();
//        initBeautys();
        initOther();

    }

    public void initCarousel() {
        NetWork.LiveBuilder(LiveHomeMethods.class).getCarousel(BaseParamsMapUtil.getParamsMap()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<HttpResponse<List<HomeCarousel>>>() {
                    @Override
                    public void accept(HttpResponse<List<HomeCarousel>> listHttpResponse) throws Exception {
                        if (listHttpResponse == null) {
                            view.onLoadCarouselSuccess(null);
                        } else {
                            view.onLoadCarouselSuccess(listHttpResponse.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.getInstance().show("getCarousel fail");
                    }
                });
    }

    public void initHot() {
        NetWork.LiveBuilder(LiveHomeMethods.class).getHotColumn(BaseParamsMapUtil.getParamsMap()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<HttpResponse<List<HomeHotColumn>>>() {
                    @Override
                    public void accept(HttpResponse<List<HomeHotColumn>> listHttpResponse) throws Exception {
                        if (listHttpResponse == null) {
                            view.onLoadHotSuccess(null);
                        } else {
                            view.onLoadHotSuccess(listHttpResponse.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.getInstance().show("getHot fail");
                        CrashHandler.getInstance().saveCrashInfo2File(throwable);
                    }
                });
    }

    public void initBeautys() {
        NetWork.LiveBuilder(LiveHomeMethods.class).getBeautys(ParamsMapUtils.getHomeFaceScoreColumn(0, 4)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<HttpResponse<List<HomeFaceScoreColumn>>>() {
                    @Override
                    public void accept(HttpResponse<List<HomeFaceScoreColumn>> listHttpResponse) throws Exception {
                        if (listHttpResponse == null) {
                            view.onLoadBeautysSuccess(null);
                        } else {
                            view.onLoadBeautysSuccess(listHttpResponse.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.getInstance().show("getFace fail");
                    }
                });
    }

    public void initOther() {
        NetWork.LiveBuilder(LiveHomeMethods.class).getHotOther(BaseParamsMapUtil.getParamsMap()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<HttpResponse<List<HomeRecommendHotCate>>>() {
                    @Override
                    public void accept(HttpResponse<List<HomeRecommendHotCate>> listHttpResponse) throws Exception {
                        if (listHttpResponse == null) {
                            view.onLoadOtherSuccess(null);
                        } else {
                            view.onLoadOtherSuccess(listHttpResponse.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.getInstance().show("getOther fail");
                    }
                });
    }
}
