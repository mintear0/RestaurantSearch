package com.example.restaurantsearch.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantsearch.viewmodel.SearchViewModel
import com.example.restaurantsearch.ui.theme.LocationDisplay

@Composable
fun SearchingScreen(
    navController: NavController,
    viewModel: SearchViewModel = viewModel()
) {
    val isCompleted by viewModel.isCompleted.collectAsState()
    LaunchedEffect(isCompleted) {
        if (isCompleted) {
            navController.navigate("result") // 次の画面へ
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("条件に合うレストランを検索中...")
        viewModel.SearchingRestaurant()
        LocationDisplay(viewModel)
    }
}