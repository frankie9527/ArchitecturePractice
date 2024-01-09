package com.jyh.sixthspace.live.view;

import com.jyh.sixthspace.sdk.bean.live.HomeCarousel;
import com.jyh.sixthspace.sdk.bean.live.HomeFaceScoreColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeHotColumn;
import com.jyh.sixthspace.sdk.bean.live.HomeRecommendHotCate;

import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */

public interface LiveReCommedView {
     void onLoadCarouselSuccess(List<HomeCarousel> list);
     void onLoadHotSuccess(List<HomeHotColumn> list);
     void onLoadBeautysSuccess(List<HomeFaceScoreColumn> list);
     void onLoadOtherSuccess(List<HomeRecommendHotCate> list);
}
