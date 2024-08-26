package com.sixth.space.ui.screen


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
@Composable
fun MainScreen(){
    SixthSpaceTheme {
        val navController = rememberNavController()
        SetupSystemUi(rememberSystemUiController(), Color.Black)
        Scaffold(
            bottomBar = { SootheBottomNavigation(navController = navController) }
        ) { padding ->
            HomeScreen(Modifier.padding(padding),navController)
        }
    }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier,navController: NavHostController) {

    NavHost(navController = navController, startDestination = "tiktok") {
        composable("tiktok") { HomeScreen(modifier) }
        composable("hot") { HotScreen(modifier,navController) }
        composable("video-detail") { VideoDetailsScreen(navController) }
    }
}
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier,navController: NavHostController) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_home_24),
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(id = R.string.home))
            },
            selected = true,
            onClick = {
                navController.navigate("tiktok")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_hot_24),
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(id = R.string.hot))
            },
            selected = false,
            onClick = {
                navController.navigate("hot")
            }
        )
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