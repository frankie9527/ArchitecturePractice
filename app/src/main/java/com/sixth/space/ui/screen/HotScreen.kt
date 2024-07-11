package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * @author: Frankie
 * @Date: 2024/7/10
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HotScreen (modifier: Modifier = Modifier) {
    val hotType = rememberPagerState(pageCount = {
        3
    })
    val hotCount = rememberPagerState(pageCount = {
        100
    })
    HorizontalPager(state = hotType,modifier = modifier) { page ->
        // Our page content vertical
        VerticalPager(state = hotCount) { verticalPage ->
            // Our page content
            Text(
                text = "hot type $page  Page: $verticalPage",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}