package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @author: Frankie
 * @Date: 2024/7/10
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen (modifier: Modifier = Modifier) {
    val tiktokType = rememberPagerState(pageCount = {
        2
    })
    HorizontalPager(state = tiktokType,modifier = modifier) { page ->
        HomeListScreen(page)
    }
}


