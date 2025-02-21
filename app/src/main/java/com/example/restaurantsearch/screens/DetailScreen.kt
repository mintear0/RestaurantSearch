package com.example.restaurantsearch.screens

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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

    Scaffold(
        topBar = { DetailTopAppBar(navController, shop) } // 上部に固定
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding) // AppBarの分だけ下にずらす
                .verticalScroll(rememberScrollState()), // スクロール可能
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (shop != null) {
                val imageUrl = shop.photo?.pc?.l
                if (imageUrl.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(256.dp)
                            .border(width = 2.dp, color = Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No\nImage")
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(256.dp)
                            .padding(bottom = 16.dp), // 画像の下に隙間を開ける
                        contentScale = ContentScale.Crop
                    )
                }

                // 住所のタイトルを中央に配置
                Text(
                    text = "住所",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp), // 住所タイトルの下に少し余白
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )

                // 住所を枠で囲んで中央に配置
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // 画面幅の90%に調整
                        .border(width = 2.dp, color = Color.Gray) // 四角の枠
                        .padding(12.dp), // 内側の余白
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${shop.address}")
                }

                // 営業時間のタイトルを中央に配置
                Text(
                    text = "営業時間",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), // 住所タイトルの下に少し余白
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )

                // 住所を枠で囲んで中央に配置
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // 画面幅の90%に調整
                        .border(width = 2.dp, color = Color.Gray) // 四角の枠
                        .padding(12.dp), // 内側の余白
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${shop.open}")
                }
            } else {
                Text(text = "お店が見つかりません")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(navController: NavController, shop: Shop?) {
    if(shop != null)
    {
        CenterAlignedTopAppBar(
            title = { shop.name?.let {
                Text(
                    text = it,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            } },

            // 「←」アイコンを設定
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    } else {
        CenterAlignedTopAppBar(
            title = {Text(text = "エラー", maxLines = 1, overflow = TextOverflow.Ellipsis)},

            // 「←」アイコンを設定
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}
