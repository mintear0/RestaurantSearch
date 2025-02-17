package com.example.restaurantsearch.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearch.deta.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val locationRepository = LocationRepository(application)

    private val _isSearchComplete = MutableLiveData<Boolean>(false)
    val isSearchComplete: LiveData<Boolean> = _isSearchComplete

    private val _isLocationComplete = MutableLiveData<Boolean>(false)
    val isLocationComplete: LiveData<Boolean> = _isLocationComplete

    private val _locationData = MutableLiveData<Pair<Double, Double>?>()
    val locationData: LiveData<Pair<Double, Double>?> = _locationData

    private var locationJob: Job? = null

    /**
     * 現在地を取得し、LiveData に保存
     */
    @SuppressLint("MissingPermission")
    fun requestLocation() {
        locationJob?.cancel()
        locationJob = viewModelScope.launch {
            val newLocation = locationRepository.fetchLocation()
            _locationData.value = newLocation
            Log.d("requestLocation", "_locationDataは${_locationData.value}, newLocationは$newLocation")
            if(_locationData.value != null)
            {
                _isLocationComplete.value = true
            }
        }
    }
    fun cancelLocationRequest() {
        locationJob?.cancel()
    }
}