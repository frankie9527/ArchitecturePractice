package com.sixth.space.data.dao

import androidx.room.ColumnInfo
import com.sixth.space.data.dao.VideoInfo.Consumption


/**
 * @author: Frankie
 * @Date: 2024/7/31
 * @Description:
 */

data class VideoDetailsInfo(
    var videoId: Int?=null,  //video id;
    var videoType: Int?=null, //
    var title: String?=null, //标题
    var description: String?=null, //标题
    var playUrl: String?=null, //播放地址
    var blurred: String?=null, //毛玻璃背景图片
    var category: String?=null,  //类别
    var cover: String?=null,//封面
    var user_name: String?=null,//username
    var user_description: String?=null,//username
    var avatar: String?=null,  //user avatar
    var releaseTime: Long?=null,//视频发布时间
    var duration: Int?=null,// video duration
    var consumption: Consumption?=null, // 视频的点赞等信息
    var likeCount: Int?=null,//评论的喜欢数
    var commentMsg: String?=null //评论
)