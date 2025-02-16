package com.example.restaurantsearch.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantsearch.viewmodel.SearchViewModel
import com.example.restaurantsearch.util.PermissionUtils

@Composable
fun SearchingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    searchViewModel: SearchViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val isLocationComplete by searchViewModel.isLocationComplete.observeAsState(false)
    val isSearchComplete by searchViewModel.isSearchComplete.observeAsState(false)

    LaunchedEffect(isLocationComplete) {
        if (isLocationComplete) {
            navController.navigate("result")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            searchViewModel.cancelLocationRequest()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (activity != null) {
            Text("条件に合うレストランを検索中...")
            if (PermissionUtils.checkLocationPermission(activity)) {
                searchViewModel.requestLocation()
                if(isLocationComplete)
                {
                    LocationDisplay(searchViewModel)
                }
            } else {
                PermissionUtils.requestLocationPermission(activity)
                Log.e("SearchingScreen", "Permissionがないよ")
            }

        } else {
            Log.e("SearchingScreen", "activityがnull")
        }
    }
}

@Composable
fun LocationDisplay(viewModel: SearchViewModel) {
    // LiveData を監視して位置情報を取得
    val location by viewModel.locationData.observeAsState()
    Log.d("LocationDisplay", "locationは$location")

    // 位置情報が更新されると自動的に表示が更新される
    location?.let {
        Text("現在地: 緯度 ${it.first}, 経度 ${it.second}")
    } ?: Text("位置情報を取得中...")
}