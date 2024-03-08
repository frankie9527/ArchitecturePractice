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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "videoId") val videoId: Int,  //video id;
    @ColumnInfo(name = "videoType") var videoType: Int, //
    @ColumnInfo(name = "title") val title: String, //标题
    @ColumnInfo(name = "description") val description: String, //标题
    @ColumnInfo(name = "playUrl") val playUrl: String, //播放地址
    @ColumnInfo(name = "blurred") val blurred: String, //毛玻璃背景图片
    @ColumnInfo(name = "category") val category: String,  //类别
    @ColumnInfo(name = "cover") val cover: String,//封面
    @ColumnInfo(name = "user_name") val user_name: String,//username
    @ColumnInfo(name = "user_description") val user_description: String,//username
    @ColumnInfo(name = "avatar") val avatar: String,  //user avatar
    @ColumnInfo(name = "releaseTime") val releaseTime: Long,//视频发布时间
    @ColumnInfo(name = "duration") val duration: Int,// video duration
    @ColumnInfo(name = "consumption") val consumption: Consumption, // 视频的点赞等信息
    @ColumnInfo(name = "likeCount") val likeCount: Int,//评论的喜欢数
    @ColumnInfo(name = "commentMsg") val commentMsg: String //评论
): Serializable{
    data class Consumption(
        @ColumnInfo(name = "collectionCount")    val collectionCount: Int,
        @ColumnInfo(name = "replyCount")    val replyCount: Int,
        @ColumnInfo(name = "shareCount")    val shareCount: Int
    ): Serializable
}