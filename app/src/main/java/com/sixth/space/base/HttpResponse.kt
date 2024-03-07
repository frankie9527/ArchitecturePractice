package com.sixth.space.base

import com.sixth.space.data.VideoInfo


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class HttpResponse<T>(
    val adExist: Boolean,
    val count: Int,
    var itemList: List<T>,
    val nextPageUrl: Any,
    val total: Int,
    var videoList: List<VideoInfo>
)