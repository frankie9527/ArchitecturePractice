package com.sixth.space.network

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 */
data class HotList(
    val itemList:List<HotItem>,
    val count: Int,
    val total: Int,
    val nextPageUrl: String,
    val adExist: Boolean
)
data class HotItem(
    val type: String,
    val trackingData: String,
    val tag: String,
    val id: Int,
    val adIndex: Int,
)