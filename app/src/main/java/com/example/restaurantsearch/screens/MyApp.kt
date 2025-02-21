package com.example.restaurantsearch.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsearch.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box() { Text( text = "グルメレンジ" , color = Color.White) }
                },
                colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
    ) { padding ->
        AppNavHost(navController, modifier, padding)
    }
}

