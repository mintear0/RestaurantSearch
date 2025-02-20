package com.example.restaurantsearch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
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

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(shop != null) {
            val imageUrl = shop.photo?.pc?.l
            if(imageUrl == null || imageUrl == "")
            {
                Box(
                    modifier
                        .size(256.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black
                        )
                ) { Text(text = "No\nImage") }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(256.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = "${shop.name}")
        } else {
            Text(text = "お店が見つかりません")
        }
    }
}