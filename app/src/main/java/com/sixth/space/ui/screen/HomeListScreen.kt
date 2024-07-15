package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.sixth.space.model.RemoteViewModel

/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeListScreen(page:Int,viewModel: RemoteViewModel = hiltViewModel()) {
    val tiktokCount = rememberPagerState(pageCount = {
        10
    })

    VerticalPager(state = tiktokCount) { verticalPage ->
        // Our page content
        Text(
            text = "Tiktok type $page  Page: $verticalPage",
            modifier = Modifier.fillMaxWidth()
        )
    }
}