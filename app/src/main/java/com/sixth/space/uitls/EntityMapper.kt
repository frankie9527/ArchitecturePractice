package com.sixth.space.uitls

import com.sixth.space.data.video.CommentItem
import com.sixth.space.data.video.HotItem
import com.sixth.space.data.video.RecommendItem
import com.sixth.space.data.dao.VideoInfo

/**
 * author: Frankie
 * Date: 2024/3/8
 * Description:
 */
fun hotItem2VideoInfo(hot: HotItem): VideoInfo {
    return VideoInfo(
        videoId = hot.data.id,
        videoType = 0,
        title = hot.data.title,
        description = hot.data.description,
        playUrl = hot.data.playUrl,
        blurred = hot.data.cover.blurred,
        category = hot.data.category,
        cover = hot.data.cover.feed,
        user_name = hot.data.author?.name.toString(),
        user_description = hot.data.author?.description.toString(),
        avatar = "",
        releaseTime = 0,
        duration = 0,
        consumption = VideoInfo.Consumption(
            hot.data.consumption.collectionCount,
            hot.data.consumption.replyCount,
            hot.data.consumption.shareCount
        ),
        likeCount = 0,
        commentMsg = ""
    )
}


fun List<HotItem>.hotList2Video(): List<VideoInfo> {
    return this.map { hotItem2VideoInfo(it) }
}

fun recommendItem2VideoInfo(recommend: RecommendItem): VideoInfo {
    return VideoInfo(
        videoId = recommend.data.id,
        videoType = 1,
        title = recommend.data.title,
        description = recommend.data.description,
        playUrl = recommend.data.playUrl,
        blurred = recommend.data.cover.blurred,
        category = recommend.data.category,
        cover = recommend.data.cover.feed,
        user_name = recommend.data.author?.name.toString(),
        user_description = recommend.data.author?.description.toString(),
        avatar = "",
        releaseTime = recommend.data.releaseTime,
        duration = recommend.data.duration,
        consumption = VideoInfo.Consumption(
            recommend.data.consumption.collectionCount,
            recommend.data.consumption.replyCount,
            recommend.data.consumption.shareCount
        ),
        likeCount = 0,
        commentMsg = ""
    )
}

fun List<RecommendItem>.recommendList2Video(): List<VideoInfo> {
    return this.map { recommendItem2VideoInfo(it) }
}

fun commentItem2VideoInfo(comment: CommentItem): VideoInfo {
    return VideoInfo(
        videoId = comment.data.videoId,
        videoType = 3,
        title = "",
        description = "",
        playUrl = "",
        blurred = "",
        category = "",
        cover = "",
        user_name = comment.data.user?.nickname.toString(),
        user_description = comment.data.user?.description.toString(),
        avatar = comment.data.user.avatar,
        releaseTime = comment.data.createTime,
        duration = 0,
        consumption = VideoInfo.Consumption(0, 0, 0),
        likeCount = comment.data.likeCount,
        commentMsg = comment.data.message
    )
}

fun List<CommentItem>.commentList2Video(): List<VideoInfo> {
    return this.map { commentItem2VideoInfo(it) }
}