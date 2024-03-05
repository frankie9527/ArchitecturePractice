package com.sixth.space.base



/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class BaseResp<T> (
    val adExist: Boolean,
    val count: Int,
    val itemList: List<T>,
    val nextPageUrl: Any,
    val total: Int
)