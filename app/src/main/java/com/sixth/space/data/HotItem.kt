package com.sixth.space.data

import java.io.Serializable

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
data class HotItem(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
) : Serializable {
    data class Data(
        val ad: Boolean,
        val adTrack: List<Any>,
        val author: Author,
        val brandWebsiteInfo: Any,
        val campaign: Any,
        val category: String,
        val collected: Boolean,
        val consumption: Consumption,
        val cover: Cover,
        val dataType: String,
        val date: Long,
        val description: String,
        val descriptionEditor: String,
        val descriptionPgc: String,
        val duration: Int,
        val favoriteAdTrack: Any,
        val id: Int,
        val idx: Int,
        val ifLimitVideo: Boolean,
        val label: Any,
        val labelList: List<Any>,
        val lastViewTime: Any,
        val library: String,
        val playInfo: List<PlayInfo>,
        val playUrl: String,
        val played: Boolean,
        val playlists: Any,
        val promotion: Any,
        val provider: Provider,
        val reallyCollected: Boolean,
        val recallSource: Any,
        val recall_source: Any,
        val releaseTime: Long,
        val remark: String,
        val resourceType: String,
        val searchWeight: Int,
        val shareAdTrack: Any,
        val slogan: String,
        val src: Any,
        val subtitles: List<Any>,
        val tags: List<Tag>,
        val thumbPlayUrl: String,
        val title: String,
        val titlePgc: String,
        val type: String,
        val videoPosterBean: Any,
        val waterMarks: Any,
        val webAdTrack: Any,
        val webUrl: WebUrl
    ) : Serializable {
        data class Author(
            val adTrack: Any,
            val approvedNotReadyVideoCount: Int,
            val description: String,
            val expert: Boolean,
            val follow: Follow,
            val icon: String,
            val id: Int,
            val ifPgc: Boolean,
            val latestReleaseTime: Long,
            val link: String,
            val name: String,
            val recSort: Int,
            val shield: Shield,
            val videoNum: Int
        ) : Serializable {
            data class Follow(
                val followed: Boolean,
                val itemId: Int,
                val itemType: String
            ): Serializable

            data class Shield(
                val itemId: Int,
                val itemType: String,
                val shielded: Boolean
            ): Serializable
        }

        data class Consumption(
            val collectionCount: Int,
            val realCollectionCount: Int,
            val replyCount: Int,
            val shareCount: Int
        ): Serializable

        data class Cover(
            val blurred: String,
            val detail: String,
            val feed: String,
            val homepage: String,
            val sharing: Any
        ): Serializable

        data class PlayInfo(
            val height: Int,
            val name: String,
            val type: String,
            val url: String,
            val urlList: List<Url>,
            val width: Int
        ) : Serializable {
            data class Url(
                val name: String,
                val size: Int,
                val url: String
            ): Serializable
        }

        data class Provider(
            val alias: String,
            val icon: String,
            val name: String
        ): Serializable

        data class Tag(
            val actionUrl: String,
            val adTrack: Any,
            val bgPicture: String,
            val childTagIdList: Any,
            val childTagList: Any,
            val communityIndex: Int,
            val desc: String,
            val haveReward: Boolean,
            val headerImage: String,
            val id: Int,
            val ifNewest: Boolean,
            val name: String,
            val newestEndTime: Long,
            val tagRecType: String
        ): Serializable

        data class WebUrl(
            val forWeibo: String,
            val raw: String
        ): Serializable
    }
}