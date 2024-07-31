package com.sixth.space.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sixth.space.model.RemoteViewModel

/**
 * @author: Frankie
 * @Date: 2024/7/27
 * @Description:
 */
@Composable
fun VideoDetailsScreen(
    navController: NavHostController,
    viewModel: RemoteViewModel = hiltViewModel()
) {

    Text(text = "VideoDetailsScreen ${viewModel.videoInfo.data}")
}