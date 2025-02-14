package com.example.restaurantsearch

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*

class LocationSensor(private val context: Context) {

    init {
        require(context is Application || context is Activity) {
            "Context must be an Application or Activity context"
        }
    }

    private val _location: MutableLiveData<Location> = MutableLiveData()
    val location: LiveData<Location> = _location

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private var locationCallback: LocationCallback? = null

    fun requestLocationPermission(activity: Activity) {
        val LOCATION_PERMISSION_REQUEST_CODE = 1001

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    fun requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("LocationSensor", "権限がない")
            return
        }

        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(5000)
            .setMaxUpdateDelayMillis(10000)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val locations = locationResult.locations
                if (locations.isNotEmpty()) {
                    val latestLocation = locations.last()
                    _location.postValue(latestLocation)
                    Log.d("LocationSensor", "位置情報を更新: ${latestLocation.latitude}, ${latestLocation.longitude}")
                    fusedLocationClient.removeLocationUpdates(this)
                } else {
                    Log.d("LocationSensor", "位置情報を取得できませんでした")
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback as LocationCallback,
            Looper.getMainLooper()
        )
    }
}

