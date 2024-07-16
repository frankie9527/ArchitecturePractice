package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.model.RemoteViewModel

/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 *
 * //    var viewState = if(page==0){
 * //        val viewState by viewModel.homeDailyState.collectAsStateWithLifecycle();
 * //    }else{
 * //        val viewState by viewModel.homeRecommendState.collectAsStateWithLifecycle();
 * //    }
 */

@Composable
fun HomeListScreen(page: Int, viewModel: RemoteViewModel = hiltViewModel()) {
    val viewState by viewModel.homeDailyState.collectAsStateWithLifecycle();

    viewState?.data?.let {
        HomeItemVideoPager(it)
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeItemVideoPager(
    videos: List<VideoInfo>
) {
    val count = rememberPagerState(pageCount = {
        videos.size
    })
    VerticalPager(state = count) { verticalPage ->
        // Our page content
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = videos.get(verticalPage).title)
        }
    }
}