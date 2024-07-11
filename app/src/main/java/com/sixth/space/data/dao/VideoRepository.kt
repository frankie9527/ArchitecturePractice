package com.sixth.space.data.dao



/**
 * @author: Frankie
 * @Date: 2024/3/9
 * @Description:
 */
interface VideoRepository {

    fun getAll(): List<VideoInfo>

    fun insertAll(list: List<VideoInfo>);

    fun getVideoByKeyWord(str:String) : List<VideoInfo>

}