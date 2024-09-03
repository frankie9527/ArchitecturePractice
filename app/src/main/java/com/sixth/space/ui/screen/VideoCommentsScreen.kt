package com.sixth.space.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.sixth.space.R
import com.sixth.space.model.RemoteViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.uitls.getTime2String

@Composable
fun VideoCommentsScreen(
    viewModel: RemoteViewModel
) {
    LaunchedEffect(true) { // Restart the effect when the pulse rate changes
        viewModel.fetchReplyState()
    }
    val viewState = viewModel.replyState.collectAsStateWithLifecycle()
    viewState.value?.data?.let {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.padding(top = 24.dp)
        ) {
            items(items = it) { item ->
                replyItem(info = item)
            }
        }
    }

}

@Composable
fun replyItem(info: VideoInfo) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (imgHead, imgLikeCount) = createRefs()
        val (nickName, postDate, lickCount, message) = createRefs()

        AsyncImage(
            model = info.avatar,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .width(30.dp)
                .height(30.dp)
                .clip(CircleShape)
                .constrainAs(imgHead) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            contentDescription = "imgHead",
            placeholder = painterResource(R.mipmap.blurry),
            error = painterResource(R.mipmap.blurry),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(R.drawable.baseline_thumb_up_24),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp, end = 16.dp)
                .width(12.dp)
                .height(12.dp)
                .constrainAs(imgLikeCount) {
                    top.linkTo(nickName.top)
                    end.linkTo(parent.end)
                }

        )
        Text(
            text = info.user_name.toString(),
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 12.dp, top = 8.dp)
                .constrainAs(nickName) {
                    top.linkTo(imgHead.top)
                    start.linkTo(imgHead.end)
                }
        )
        Text(text = info.releaseTime!!.getTime2String(),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .constrainAs(postDate) {
                    top.linkTo(nickName.top)
                    start.linkTo(nickName.end)
                })
        Text(text = info.likeCount.toString(), fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(end = 4.dp, top = 8.dp)
                .constrainAs(lickCount) {
                    end.linkTo(imgLikeCount.start)

                })

        Text(text = info.commentMsg.toString(),
            fontSize = 16.sp,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier
                .padding(top = 8.dp, start = 12.dp, end = 48.dp)
                .constrainAs(message) {
                    top.linkTo(nickName.bottom)
                    start.linkTo(nickName.start)
                })
    }
}