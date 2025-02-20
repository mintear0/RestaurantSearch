package com.example.restaurantsearch.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantsearch.deta.Shop
import com.example.restaurantsearch.viewmodel.RestaurantViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    restaurantViewModel: RestaurantViewModel,
    shopId: String
) {
    val restaurants: List<Shop> by restaurantViewModel.restaurants.observeAsState(initial = emptyList())
    val shop = restaurants.find { it.id == shopId }

    Column(modifier = modifier.fillMaxSize()) {
        if(shop != null) {
            Text(text = "${shop.name}")
        } else {
            Text(text = "お店が見つかりません")
        }
    }
}