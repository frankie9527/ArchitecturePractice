package com.sixth.space.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sixth.space.R
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.model.RemoteViewModel


/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 */

@Composable
fun HotListScreen(
    page: Int, navController: NavHostController,
    viewModel: RemoteViewModel = hiltViewModel()
) {
    val viewState = when (page) {
        0 -> {
            viewModel.hotWeeklyState.collectAsStateWithLifecycle()
        }

        1 -> {
            viewModel.hotMonthlyState.collectAsStateWithLifecycle()
        }

        else -> {
            viewModel.hotHistoricalState.collectAsStateWithLifecycle()
        }
    }
    viewState.value?.data?.let {
        LazyColumn {

            items(items = it) { item ->
                HomeItemView(video = item, navController, viewModel)
            }
        }
    }
}

@Composable
fun HomeItemView(
    video: VideoInfo,
    navController: NavHostController,
    viewModel: RemoteViewModel
) {
    ConstraintLayout(Modifier.clickable(onClick = {
        viewModel.videoInfo.data=video.playUrl
        navController.navigate("video-detail")
    })) {
        val (title, type) = createRefs()
        AsyncImage(
            model = video.cover,
            modifier = Modifier.height(180.dp),
            contentDescription = "hot list item background",
            placeholder = painterResource(R.mipmap.blurry),
            error = painterResource(R.mipmap.blurry),
            contentScale = ContentScale.FillWidth
        )
        Text(
            video.title,
            Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            "#" + video.category,
            Modifier.constrainAs(type) {
                top.linkTo(title.bottom)
                start.linkTo(title.start)
                end.linkTo(parent.end)
            },
            color = Color.White,
            fontSize = 12.sp

        )
    }
}