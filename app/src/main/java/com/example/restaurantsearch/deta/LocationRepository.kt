package com.example.restaurantsearch.deta

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource

class LocationRepository(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _locationData = MutableLiveData<Pair<Double, Double>?>()
    val locationData: LiveData<Pair<Double, Double>?> = _locationData

    @SuppressLint("MissingPermission")
    fun fetchLocation() {
        val cancellationToken = CancellationTokenSource().token
        fusedLocationClient.getCurrentLocation(
            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken
        ).addOnSuccessListener { location: Location? ->
            location?.let {
                _locationData.postValue(Pair(it.latitude, it.longitude))
            } ?: run {
                _locationData.postValue(null)
            }
        }
    }
}