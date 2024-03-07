package com.sixth.space.uitls

import com.sixth.space.data.CommentItem
import com.sixth.space.data.HotItem
import com.sixth.space.data.RecommendItem
import com.sixth.space.data.VideoInfo

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
        category = hot.data.category,
        cover = hot.data.cover.feed,
        name = "",
        avatar = "",
        releaseTime = 0,
        duration = 0,
        likeCount = 0,
        commentMsg = ""
    )
}


fun List<HotItem>.hotList2Video(): List<VideoInfo> {
    return this.map { hotItem2VideoInfo(it) }
}

fun recommendItem2VideoInfo(recommend: RecommendItem): VideoInfo {
    val n=recommend.data.author?.name+"";
    return VideoInfo(
        videoId = recommend.data.id,
        videoType = 1,
        title = recommend.data.title,
        category = recommend.data.category,
        cover = recommend.data.cover.feed,
        name = n,
        avatar = "",
        releaseTime = recommend.data.releaseTime,
        duration = recommend.data.duration,
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
        category = "",
        cover = "",
        name = comment.data.user.nickname,
        avatar = comment.data.user.avatar,
        releaseTime = comment.data.createTime,
        duration = 0,
        likeCount = comment.data.likeCount,
        commentMsg = comment.data.message
    )
}

fun List<CommentItem>.commentList2Video(): List<VideoInfo> {
    return this.map { commentItem2VideoInfo(it) }
}