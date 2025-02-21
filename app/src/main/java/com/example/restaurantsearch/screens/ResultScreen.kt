package com.example.restaurantsearch.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.restaurantsearch.deta.Shop
import com.example.restaurantsearch.util.DistanceMeasure.distanceFilter
import com.example.restaurantsearch.util.DistanceMeasure.distanceMeasure
import com.example.restaurantsearch.viewmodel.RestaurantViewModel
import com.example.restaurantsearch.viewmodel.SearchViewModel


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    restaurantViewModel: RestaurantViewModel,
    searchViewModel: SearchViewModel,
    range: String
) {
    val restaurants by restaurantViewModel.restaurants.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = { // フローティングボタン追加
            ExtendedFloatingActionButton(
                onClick = { navController.navigate("condition") }, // ConditionScreen へ遷移
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Search, contentDescription = "条件を変更")
                Text(text = "検索条件を変更")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding) // AppBarやFABの分の余白を考慮
        ) {
            if (restaurants.isEmpty()) {
                Text("検索範囲を広げるか、予算を変更してください")
            } else {
                LazyColumn {
                    items(restaurants) { shop ->
                        RestaurantItem(modifier, navController, shop, range, searchViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantItem(modifier: Modifier, navController: NavController, shop: Shop, range: String, searchViewModel: SearchViewModel) {
    if(distanceFilter(shop, range, searchViewModel)){
        Log.d("RestaurantItem", range)
        val imageUrl = shop.photo?.pc?.m
        Card(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    // DetailScreen に遷移し、shop の情報を渡す
                    navController.navigate(
                        "detail/${shop.id}"
                    )
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier.padding(16.dp),) {
                Row(modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(imageUrl == null || imageUrl == "")
                    {
                        Box(
                            modifier
                                .size(48.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                ),
                            contentAlignment = Alignment.Center
                        ) { Text(text = "No\nImage") }
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "ここから ${distanceMeasure(shop, searchViewModel)}m",
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Text(text = shop.name ?: "店名不明", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = shop.mobile_access ?: "アクセス不明")
                Text(text = "予算: ${shop.budget?.name ?: "予算不明"}")
            }
        }
    }
}