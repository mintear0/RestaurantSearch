package com.example.restaurantsearch.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantsearch.R
import com.example.restaurantsearch.deta.Shop
import com.example.restaurantsearch.screens.SearchingScreen
import com.example.restaurantsearch.util.DistanceMeasure.distanceFilter
import com.example.restaurantsearch.viewmodel.RestaurantViewModel
import com.example.restaurantsearch.viewmodel.SearchViewModel


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    //navController: NavController,
    restaurantViewModel: RestaurantViewModel = viewModel(),
    range: String
    ) {
    val restaurants by restaurantViewModel.restaurants.observeAsState(emptyList())

    Column(modifier = modifier.fillMaxSize()) {
        if (restaurants.isEmpty()) {
            Text("検索範囲を広げるか、予算を増やしてください")
        } else {
            LazyColumn {
                items(restaurants) { shop ->
                    RestaurantItem(shop, range)
                }
            }
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun RestaurantItem(shop: Shop, range: String) {
    val searchViewModel: SearchViewModel = viewModel()
    if(!distanceFilter(shop, range, searchViewModel)){
        Log.d("RestaurantItem", range)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = shop.name ?: "店名不明", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = shop.address ?: "住所不明")
                Text(text = "予算: ${shop.budget?.name ?: "不明"}")
            }
        }
    }
}

@Composable
private fun CardContent(
    name: String,
    searchViewModel: SearchViewModel = viewModel()
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                LocationDisplay(searchViewModel)
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.AutoMirrored.Filled.ArrowBack else Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}