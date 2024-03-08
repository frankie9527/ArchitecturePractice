package com.sixth.space.base




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

)