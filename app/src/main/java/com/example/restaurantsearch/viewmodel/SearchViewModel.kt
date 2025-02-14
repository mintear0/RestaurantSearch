package com.example.restaurantsearch.viewmodel

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearch.LocationSensor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){
    var distance by mutableStateOf("")
        private set

    fun updateDistance(input: String) {
        if(input.all { it.isDigit() }) {
            distance = input
        }
    }

    var money by mutableStateOf("")
        private set

    fun updateMoney(input: String) {
        if(input.all { it.isDigit() }) {
            money = input
        }
    }

    private val _isCompleted = MutableStateFlow(false)
    val isCompleted: StateFlow<Boolean> = _isCompleted

    private var locationSensor: LocationSensor? = null

    fun initLocationSensor(Activity: Activity){
        locationSensor = LocationSensor(Activity)
    }

    fun SearchingRestaurant() {
        viewModelScope.launch {
            locationSensor?.let{ gps ->
                // 位置情報を取得
                gps.requestLocationPermission()
                gps.requestLocationUpdate()

                locationSensor.location.asFlow()
                    .filterNotNull()
                    .collect { location ->
                        _locationData.value = Pair(location.latitude, location.longitude)
                    }

            }

            // ここでAPIリクエストや他の処理を行う
            delay(3000) // 例: 3秒待機 (本来はAPIレスポンスを待つ)
            _isCompleted.value = true
        }
        println(_locationData.value)
    }
}