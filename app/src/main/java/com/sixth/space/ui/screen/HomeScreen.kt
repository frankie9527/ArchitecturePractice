package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
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
fun HomeScreen(modifier: Modifier = Modifier) {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (tab, pager) = createRefs()
        val hotList: Array<String> =
            LocalContext.current.resources.getStringArray(R.array.home_array);
        val tiktokPagerState = rememberPagerState(
            initialPage = 0,
            pageCount = {
                2
            })
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = tiktokPagerState.currentPage,
            containerColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[tiktokPagerState.currentPage])
                        .padding(horizontal = 24.dp),
                    color = Color.White
                )
            },
            modifier = modifier.constrainAs(tab) {
                top.linkTo(parent.top)
            }) {
            hotList.forEachIndexed() { index, item ->
                Tab(selected = false, onClick = {
                    coroutineScope.launch {
                        tiktokPagerState.animateScrollToPage(index)
                    }
                }, text = {
                    val textColor=if (tiktokPagerState.currentPage==index){
                        Color.White
                    }else{
                        Color.Gray
                    }
                    Text(text = item, color =textColor )
                })
            }
        }
        HorizontalPager(state = tiktokPagerState, modifier = modifier.constrainAs(pager) {
            top.linkTo(tab.bottom)
        }) { page ->
            HomeListScreen(page = page)
        }
    }

}


