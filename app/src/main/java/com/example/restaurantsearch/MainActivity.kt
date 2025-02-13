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
import androidx.compose.material3.Text
import androidx.compose.ui.*
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

    fun location
        /*
        val locationSensor = LocationSensor(this)
        locationSensor.requestLocationPermission(this)

        button.setOnClickListener {
            locationSensor.requestLocationUpdate()
        }

        // LiveDataの値が変化をobserverが監視、プログラムを実行する
        locationSensor.location.observe(this, Observer {
            Text(text = "${it.latitude}\n, ${it.longitude}")
        })
        */
    }

}