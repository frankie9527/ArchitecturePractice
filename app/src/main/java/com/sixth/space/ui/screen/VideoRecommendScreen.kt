package com.sixth.space.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sixth.space.model.RemoteViewModel

@Composable
fun VideoRecommendScreen(
    navController: NavHostController,
    viewModel: RemoteViewModel
) {
    Text(text = "VideoRecommendScreen ${viewModel.videoInfo.data}")
}