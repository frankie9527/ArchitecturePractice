package com.sixth.space.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sixth.space.R
import com.sixth.space.data.dao.VideoDetailsInfo
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.model.RemoteViewModel
import com.sixth.space.uitls.durationToStr
import com.sixth.space.uitls.getTime2String

@SuppressLint("SuspiciousIndentation")
@Composable
fun VideoRecommendScreen(
    navController: NavHostController,
    viewModel: RemoteViewModel
) {
    LaunchedEffect(true) { // Restart the effect when the pulse rate changes
        viewModel.fetchRecommend()
    }
    val viewState = viewModel.recommendState.collectAsStateWithLifecycle()

        viewState.value?.data?.let {
            LazyColumn {
                item {
                    head(viewModel.info)
                }
                items(items = it) { item ->
                    body(item)
                }
            }
    }
}


@Composable
fun head(info: VideoDetailsInfo) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
    ) {
        val (title, releaseDate, category, description) = createRefs()
        val cs = createRef()
        Text(
            text = info.title.toString(),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = info.releaseTime!!.getTime2String(),
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .constrainAs(releaseDate) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "#" + info.category,
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .constrainAs(category) {
                    top.linkTo(title.bottom)
                    start.linkTo(releaseDate.end)
                }
        )
        Text(
            text = info.description.toString(),
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                .constrainAs(description) {
                    top.linkTo(releaseDate.bottom)
                    start.linkTo(parent.start)
                }
        )
        ConstraintLayout(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .constrainAs(cs) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                }) {
            val (imgHead, author, authorDescription, rowFollow) = createRefs()
            AsyncImage(
                model = info.avatar,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(40.dp)
                    .height(40.dp)
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

            Text(
                text = info.user_name.toString(),
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(author) {
                        top.linkTo(imgHead.top)
                        start.linkTo(imgHead.end)
                    }
            )

            Text(
                text = info.user_description.toString(),
                maxLines = 1,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 100.dp)
                    .constrainAs(authorDescription) {
                        bottom.linkTo(imgHead.bottom)
                        start.linkTo(imgHead.end)
                    }
            )

            Row(
                Modifier
                    .padding(end = 16.dp)
                    .constrainAs(rowFollow) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)

                    }) {
                Image(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 2.4.dp, end = 2.dp)
                        .width(8.dp)
                        .height(8.dp)
                )
                Text(
                    text = "follow",
                    maxLines = 1,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
        }
    }
}


@Composable
fun body(info: VideoInfo) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val (imgCover,duration, title, author, date, category) = createRefs()
        // Create guideline from the start of the parent at 10% the width of the Composable
        val startGuideline = createGuidelineFromStart(0.4f)
        AsyncImage(
            model = info.avatar,
            modifier = Modifier
                .height(90.dp)
                .constrainAs(imgCover) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(startGuideline)
                    width= Dimension.preferredWrapContent
                },
            contentDescription = "imgHead",
            placeholder = painterResource(R.mipmap.blurry),
            error = painterResource(R.mipmap.blurry),
            contentScale= ContentScale.Crop
        )

        Text(
            text = info.duration.durationToStr(),
            color = Color.White,
            fontSize = 11.sp,
            modifier = Modifier.padding(end = 8.dp)
                .constrainAs(duration) {
                    bottom.linkTo(imgCover.bottom)
                    end.linkTo(imgCover.end)
                }
        )
        Text(
            text = info.title,
            color = Color.White,
            fontSize = 14.sp,
            maxLines = 2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(imgCover.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            style = TextStyle(textAlign = TextAlign.Start)
        )

        Text(
            text = info.releaseTime.getTime2String(),
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(date) {
                    bottom.linkTo(imgCover.bottom)
                    start.linkTo(imgCover.end)
                },
        )

        Text(
            text = info.user_name,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(author) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(date.top)
                    start.linkTo(imgCover.end)
                }
        )

        Text(
            text = "# "+info.category,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(category) {
                    top.linkTo(author.top)
                    end.linkTo(parent.end)
                }
        )
    }
}



