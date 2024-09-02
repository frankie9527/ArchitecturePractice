package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import com.sixth.space.R
import kotlinx.coroutines.launch

/**
 * @author: Frankie
 * @Date: 2024/7/10
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier,pagerState: PagerState) {
    Column(modifier = modifier) {
        HorizontalPager(state = pagerState) { page ->
            HomeListScreen(page = page)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTitleList(pagerState: PagerState){
    val hotList: Array<String> =
        LocalContext.current.resources.getStringArray(R.array.home_array);

    val coroutineScope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage,
        containerColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                ,
                color = Color.White
            )
        }) {
        hotList.forEachIndexed() { index, item ->
            Tab(selected = false, onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }, text = {
                val textColor=if (pagerState.currentPage==index){
                    Color.White
                }else{
                    Color.Gray
                }
                Text(text = item, color =textColor )
            })
        }
    }
}

