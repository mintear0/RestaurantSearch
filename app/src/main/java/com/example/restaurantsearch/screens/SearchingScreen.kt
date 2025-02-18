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
import com.example.restaurantsearch.deta.Budget
import com.example.restaurantsearch.deta.SearchData
import com.example.restaurantsearch.viewmodel.SearchViewModel
import com.example.restaurantsearch.util.PermissionUtils
import com.example.restaurantsearch.viewmodel.RestaurantViewModel

@Composable
fun SearchingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    searchViewModel: SearchViewModel = viewModel(),
    restaurantViewModel: RestaurantViewModel = viewModel(),
    budget: String,
    range: String
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val isLocationComplete by searchViewModel.isLocationComplete.observeAsState(false)
    val restaurants by restaurantViewModel.restaurants.observeAsState(emptyList())
    val location by searchViewModel.locationData.observeAsState()


    val feeCode = budgetSelector(budget)
    val rangeNumber = rangeSelector(range)
    val apiKey = "409d3d0e0aae1587"

    LaunchedEffect(isLocationComplete) {
        if (isLocationComplete) {
            Log.d("SearchingScreen", "isLocationCompleteはTrueだよ")
            location?.let { (lat, lng) ->
                val searchData = SearchData(
                    key = apiKey,
                    feeCode = feeCode,
                    range = rangeNumber,
                    lat = lat,
                    lng = lng,
                    format = "json"
                )
                restaurantViewModel.getRestaurants(searchData)
                Log.d("SearchingScreen", "getRestaurantsは通過したよ")
                Log.d("searchData","budget={$budget}range={$feeCode}")
                Log.d("API_REQUEST", "URL: https://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=$apiKey&budget=$feeCode&range=$rangeNumber&lat=$lat&lng=$lng&format=json")
            }
        }
    }

    // restaurantsのデータが取得できたらResultScreenへ移動
    LaunchedEffect(restaurants) {
        if (restaurants.isNotEmpty()) {
            Log.d("SearchingScreen", "restaurantsは{$restaurants}だよ")
            navController.navigate("result")
        }
        Log.d("SearchingScreen", "restaurantsは{$restaurants}だよ")
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
            if (PermissionUtils.checkLocationPermission(activity)) {
                Text("条件に合うレストランを検索中...")
                searchViewModel.requestLocation()
                Log.d("SearchingScreen", "requestLocation()は動いたよ")
            } else {
                Text("位置情報の利用が許可されていません。設定から位置情報の許可をしてください。")
                Log.e("SearchingScreen", "Permissionがないよ")
            }

        } else {
            Log.e("SearchingScreen", "activityがnull")
        }
    }
}

@Composable
fun budgetSelector(budget: String): String {
    return if(budget.toInt() <= 500) {
        "B009"
    } else if(budget.toInt() <= 1000) {
        "B010"
    } else if(budget.toInt() <= 1500) {
        "B011"
    } else if(budget.toInt() <= 2000) {
        "B001"
    } else if(budget.toInt() <= 3000) {
        "B002"
    } else if(budget.toInt() <= 4000) {
        "B003"
    } else if(budget.toInt() <= 5000) {
        "B008"
    } else if(budget.toInt() <= 7000) {
        "B004"
    } else if(budget.toInt() <= 10000) {
        "B005"
    } else if(budget.toInt() <= 15000) {
        "B006"
    } else if(budget.toInt() <= 20000) {
        "B012"
    } else if(budget.toInt() <= 30000) {
        "B013"
    } else {
        "B014"
    }
}

@Composable
fun rangeSelector(range: String): Int {
    return if(range.toInt() < 300) {
        1
    } else if(range.toInt() < 500) {
        2
    } else if(range.toInt() < 1000) {
        3
    } else if(range.toInt() < 2000) {
        4
    } else {
        5
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