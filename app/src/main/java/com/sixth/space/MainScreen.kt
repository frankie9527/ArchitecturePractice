package com.sixth.space


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sixth.space.ui.screen.HotScreen
import com.sixth.space.ui.screen.TikTokScreen
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
        composable("tiktok") { TikTokScreen(modifier) }
        composable("hot") { HotScreen(modifier) }
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
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            },
            label = {
                Text("tiktok")
            },
            selected = true,
            onClick = {
                navController.navigate("tiktok")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text("hot")
            },
            selected = false,
            onClick = {
                navController.navigate("hot")
            }
        )
    }
}
