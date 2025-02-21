package com.example.restaurantsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.*
import com.example.restaurantsearch.screens.MyApp
import com.example.restaurantsearch.ui.theme.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}