@file:OptIn(ExperimentalFoundationApi::class)

package com.sixth.space.ui.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sixth.space.R
import com.sixth.space.ui.theme.SixthSpaceTheme

/**
 * @author: Frankie
 * @Date: 2024/7/10
 * @Description:
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    SixthSpaceTheme {
        val navController = rememberNavController()
        val homePagerState = rememberPagerState(
            initialPage = 0,
            pageCount = {
                2
            })
        val hotPagerState = rememberPagerState(
            initialPage = 0,
            pageCount = {
                3
            })
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val routes = remember { "home,hot" }

        SetupSystemUi(rememberSystemUiController(), Color.Black)
        Scaffold(
            topBar = {
                if (!routes.contains(currentRoute.toString())) {
                    return@Scaffold
                }
                val pagerState = if (currentRoute == "home") {
                    homePagerState
                } else {
                    hotPagerState
                }
                StatusBar(
                    pagerState = pagerState,
                    navController,
                    currentRoute = currentRoute.toString()
                )
            },
            bottomBar = {
                if (!routes.contains(currentRoute.toString())) {
                    return@Scaffold
                }
                SootheBottomNavigation(
                    navController = navController,
                    currentRoute = currentRoute.toString()
                )
            }
        ) { padding ->
            HomeScreen(Modifier.padding(padding), navController, homePagerState, hotPagerState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homePagerState: PagerState,
    hotPagerState: PagerState
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(modifier, homePagerState) }
        composable("hot") { HotScreen(modifier, navController, hotPagerState) }
        composable("video-detail") { VideoDetailsScreen(navController) }
        composable("search") { SearchScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatusBar(
    pagerState: PagerState,
    navController: NavHostController,
    currentRoute: String
) {
    TopAppBar(
        title = {
            if (currentRoute == "home") {
                HomeScreenTitleList(pagerState = pagerState)
            } else {
                HotScreenTitleList(pagerState = pagerState)
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(Icons.Filled.Menu, null, tint = Color.White)
            }
        },
        actions = {
            IconButton(
                onClick = { navController.navigate("search") }
            ) {
                Icon(Icons.Filled.Search, null, tint = Color.White)
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

@Composable
private fun SootheBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentRoute: String
) {

    val bkg = if (currentRoute == "home") {
        Color.Black
    } else {
        Color.White
    }
    NavigationBar(
        containerColor = bkg,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_home_24),
                    contentDescription = null,
                    tint = getBackColor(currentRoute,0),

                    )
            },
            label = {
                Text(
                    stringResource(id = R.string.home),
                    color = getBackColor(currentRoute,0)
                )
            },
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_hot_24),
                    contentDescription = null,
                    tint = getBackColor(currentRoute,1),
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.hot), color = getBackColor(currentRoute,1)
                )
            },
            selected = currentRoute == "hot",
            onClick = {
                navController.navigate("hot") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
        )
    }
}

fun getBackColor(currentRoute: String,index:Int): Color {
    return if (index==0){
        if (currentRoute == "home") {
            Color.White
        } else {
            Color.Gray
        }
    }else{
        if (currentRoute == "hot") {
            Color.Black
        } else {
            Color.Gray
        }
    }
}

@Composable
fun SetupSystemUi(
    systemUiController: SystemUiController,
    systemBarColor: Color
) {
    SideEffect {
        systemUiController.setSystemBarsColor(color = systemBarColor)
    }
}