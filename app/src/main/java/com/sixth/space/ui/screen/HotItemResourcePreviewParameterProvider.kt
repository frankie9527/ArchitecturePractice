package com.sixth.space.ui.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sixth.space.data.dao.VideoInfo

class HotItemResourcePreviewParameterProvider : PreviewParameterProvider<VideoInfo>{
    override val values: Sequence<VideoInfo> = sequenceOf(
         VideoInfo(title = "Life is so wonderful!",
             category = "life",
             cover = "https://ww3.sinaimg.cn/mw690/8a9320f3ly1hs555l5cupj21hc0u0qoz.jpg"
         )
    )
}