package com.sixth.space.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 * videoType 为 recycler_adapter_type_hot，recycler_adapter_type_recommend，recycler_adapter_type_comment
 *
 */

@Entity
data class VideoInfo(
    @PrimaryKey
    @ColumnInfo(name = "videoId") var videoId: Int ?=null,  //video id;
    @ColumnInfo(name = "videoType") var videoType: Int?=null, //
    @ColumnInfo(name = "title") var title: String?=null, //标题
    @ColumnInfo(name = "description") var description: String?=null, //标题
    @ColumnInfo(name = "playUrl") var playUrl: String?=null, //播放地址
    @ColumnInfo(name = "blurred") var blurred: String?=null, //毛玻璃背景图片
    @ColumnInfo(name = "category") var category: String?=null,  //类别
    @ColumnInfo(name = "cover") var cover: String?=null,//封面
    @ColumnInfo(name = "user_name") var user_name: String?=null,//username
    @ColumnInfo(name = "user_description") var user_description: String?=null,//username
    @ColumnInfo(name = "avatar") var avatar: String?=null,  //user avatar
    @ColumnInfo(name = "releaseTime") var releaseTime: Long=0,//视频发布时间
    @ColumnInfo(name = "duration") var duration: Int=0,// video duration
    @ColumnInfo(name = "consumption") var consumption: Consumption?=null, // 视频的点赞等信息
    @ColumnInfo(name = "likeCount") var likeCount: Int=0,//评论的喜欢数
    @ColumnInfo(name = "commentMsg") val commentMsg: String?=null //评论
): Serializable{
    data class Consumption(
        @ColumnInfo(name = "collectionCount")    val collectionCount: Int?=null,
        @ColumnInfo(name = "replyCount")    val replyCount: Int?=null,
        @ColumnInfo(name = "shareCount")    val shareCount: Int?=null
    ): Serializable
}