package com.example.restaurantsearch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import androidx.compose.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsearch.navigation.AppNavHost
import com.example.restaurantsearch.screens.MyApp
import com.example.restaurantsearch.ui.theme.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantSearchTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }


}