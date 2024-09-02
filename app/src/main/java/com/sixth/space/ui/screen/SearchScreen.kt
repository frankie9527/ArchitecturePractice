package com.sixth.space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sixth.space.R

import com.sixth.space.model.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, viewModel: SearchViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ){
        AsyncImage(
            model = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentDescription = "hot list item background",
            placeholder = painterResource(R.mipmap.blurry),
            error = painterResource(R.mipmap.blurry),
            contentScale = ContentScale.FillHeight
        )
        val viewState = viewModel.searchState.collectAsStateWithLifecycle()
        var query by remember { mutableStateOf(TextFieldValue()) }
        Column {
            TopAppBar(
                title = {
                    TextField(
                        value = query,
                        onValueChange = { query = it
                                        viewModel.search(query.text)},
                        singleLine = true,
                        placeholder = {
                            Text(text = "search")
                        },
                        modifier = Modifier
                            .background(Color.Black) // 设置背景色
                            .fillMaxWidth()// 可选的内边距
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                              navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
            )

            viewState.value?.data?.let {
                LazyColumn {
                    items(items = it) { item ->
                        body(info = item)
                    }
                }
            }
        }
    }
}