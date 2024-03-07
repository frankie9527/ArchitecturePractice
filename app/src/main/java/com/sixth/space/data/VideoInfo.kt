package com.sixth.space.data




/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 * videoType 为 recycler_adapter_type_hot，recycler_adapter_type_recommend，recycler_adapter_type_comment
 *
 */


data class VideoInfo(
    val videoId:Int ,  //video id;
    val videoType:Int , //
    val title:String,
    val category:String,  //类别
    val cover:String,//封面
    val name:String ,//username
    val avatar:String,  //user avatar
    val releaseTime :Long ,//视频发布时间
    val duration :Int ,// video duration
    val likeCount:Int  ,//评论的喜欢数
    val commentMsg:String //评论
)