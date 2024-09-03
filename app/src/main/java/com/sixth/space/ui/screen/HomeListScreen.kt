package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bumptech.glide.Glide
import com.sixth.space.R
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.model.RemoteViewModel
import com.sixth.space.ui.view.HomeVideoView

/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 *
 */

@Composable
fun HomeListScreen(page: Int, viewModel: RemoteViewModel = hiltViewModel(key = page.toString())) {
    LaunchedEffect(true) { // Restart the effect when the pulse rate changes
        viewModel.fetchHomeState(page)
    }
    val viewState = viewModel.homeState.collectAsStateWithLifecycle()
    viewState.value?.data?.let {
        HomeItemPager(it)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeItemPager(
    videos: List<VideoInfo>
) {
    val state = rememberPagerState(pageCount = {
        videos.size
    })
    VerticalPager(state = state) { verticalPage ->
        // Our page content
        HomeItemView(videos[verticalPage], state, verticalPage)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeItemView(video: VideoInfo, state: PagerState, pageIndex: Int) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Create guideline from the start of the parent at 10% the width of the Composable
        val endGuideline = createGuidelineFromStart(0.8f)
        val (columnBottom, columnEnd) = createRefs()
        videoView(url = video.playUrl.toString(), cover = video.cover.toString(), state, pageIndex)
        Column(modifier = Modifier
            .constrainAs(columnBottom) {
                start.linkTo(parent.start)
                end.linkTo(endGuideline)
                bottom.linkTo(parent.bottom)
                width = Dimension.preferredWrapContent
            }) {
            Text(
                text = "@" + video.user_name,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                maxLines = 2,
                text = video.description.toString(),
                fontSize = 18.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columnEnd) {
                    start.linkTo(endGuideline)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.preferredWrapContent
                },
        ) {
            Box(modifier = Modifier.height(60.dp)) {
                AsyncImage(
                    model = video.avatar,
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(CircleShape),
                    contentDescription = "imgHead",
                    placeholder = painterResource(R.mipmap.blurry),
                    error = painterResource(R.mipmap.blurry),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.baseline_add_circle_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Image(
                painter = painterResource(R.drawable.baseline_favorite_24),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = video.consumption?.collectionCount.toString(),
                modifier = Modifier.padding(top = 2.dp),
                color = Color.White
            )

            Image(
                painter = painterResource(R.mipmap.ic_comment),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = video.consumption?.replyCount.toString(),
                modifier = Modifier.padding(top = 2.dp),
                color = Color.White
            )

            Image(
                painter = painterResource(R.mipmap.ic_share),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = "分享", modifier = Modifier.padding(top = 2.dp),
                color = Color.White
            )

            AsyncImage(
                model = video.avatar,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 32.dp)
                    .size(42.dp)
                    .clip(CircleShape),
                contentDescription = "imghead",
                placeholder = painterResource(R.mipmap.blurry),
                error = painterResource(R.mipmap.blurry),
                contentScale = ContentScale.Crop
            )
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun videoView(
    url: String,
    cover: String,
    state: PagerState,
    pageIndex: Int
) {
    if (state.settledPage != pageIndex) {
        return
    }
    val context = LocalContext.current
    val videoView = remember {
        HomeVideoView(context).apply {
            Glide.with(this).load(cover).into(img_back_ground)
        }
    }
    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current);
    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    videoView.setDataAndPlay(url)
                }

                Lifecycle.Event.ON_RESUME -> videoView.resume()
                Lifecycle.Event.ON_PAUSE -> videoView.pause()
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = {
            videoView
        },
        onRelease = {
            videoView.release()
        }
    )


}