package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HotListScreen(page:Int) {
    val hotCount = rememberPagerState(pageCount = {
        100
    })
    VerticalPager(state = hotCount) { verticalPage ->
        // Our page content
        Text(
            text = "Hot type $page  Page: $verticalPage",
            modifier = Modifier.fillMaxWidth()
        )
    }
}