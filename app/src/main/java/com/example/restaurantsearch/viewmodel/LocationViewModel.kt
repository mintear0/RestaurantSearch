package com.example.restaurantsearch.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationRepository = MutableLiveData<Pair<Double, Double>>()

    val locationData = locationRepository.locationData

    fun requestLocation() {
        viewModelScope.launch {
            locationRepository.fetchLocation()
        }
    }
}