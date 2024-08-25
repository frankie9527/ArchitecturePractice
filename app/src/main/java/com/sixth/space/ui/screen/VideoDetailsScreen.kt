package com.sixth.space.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sixth.space.R
import com.sixth.space.model.RemoteViewModel
import kotlinx.coroutines.launch
import org.various.player.ui.simple.SimpleVideoView

/**
 * @author: Frankie
 * @Date: 2024/7/27
 * @Description:
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoDetailsScreen(
    navController: NavHostController,
    viewModel: RemoteViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentDescription = "hot list item background",
            placeholder = painterResource(R.mipmap.blurry),
            error = painterResource(R.mipmap.blurry),
            contentScale = ContentScale.FillHeight
        )
        Column {
            videoView(viewModel.videoInfo.data)
            val videoDetailsList: Array<String> =
                LocalContext.current.resources.getStringArray(R.array.video_details_array);
            val videoDetailsState = rememberPagerState(
                initialPage = 0,
                pageCount = {
                    2
                })
            val coroutineScope = rememberCoroutineScope()

            TabRow(selectedTabIndex = videoDetailsState.currentPage,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[videoDetailsState.currentPage]),
                        color = Color.Transparent
                    )
                }) {
                videoDetailsList.forEachIndexed() { index, item ->
                    Tab(selected = false, onClick = {
                        coroutineScope.launch {
                            videoDetailsState.animateScrollToPage(index)
                        }
                    }, text = {
                        val textColor = if (videoDetailsState.currentPage == index) {
                            Color.White
                        } else {
                            Color.Gray
                        }
                        Text(text = item, color = textColor)

                    })
                }
            }

            HorizontalPager(state = videoDetailsState) { page ->
                when (page) {
                    0 -> {
                        VideoRecommendScreen(navController, viewModel)
                    }

                    else -> {
                        VideoCommentsScreen(id = "")
                    }
                }
            }
        }
    }

}

@Composable
fun videoView(url:String){
    val context = LocalContext.current
    var md: Modifier? = null
    val configuration = LocalConfiguration.current
    md = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Modifier
            .fillMaxWidth()
            .height(240.dp)
    } else {
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    }
    val simpleVideoView = remember {
        SimpleVideoView(context).apply {
            // Sets up listeners for View -> Compose communication
            setUserActionListener {
                Toast.makeText(context, "nihao", Toast.LENGTH_LONG).show()
            }
        }
    }
    // Adds view to Compose
    AndroidView(
        modifier = md, // Occupy the max size in the Compose UI tree
        factory = { context ->
            // Creates view
            simpleVideoView
        },
        update = { view ->
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication
                view.setPlayData(url, "update view")
                view.startSyncPlay()
        },
        onRelease = { view ->
            view.release()
        }
    )
}
