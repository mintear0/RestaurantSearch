package com.example.restaurantsearch.deta

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await

class LocationRepository(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    var newLocation: Location? = null

    @SuppressLint("MissingPermission")
    suspend fun fetchLocation(): Pair<Double, Double>? {
        return try {
                val priority = Priority.PRIORITY_HIGH_ACCURACY
                val cancellationTokenSource = CancellationTokenSource()
                newLocation = fusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token).await()

                newLocation?.let { Pair(it.latitude, it.longitude) }

        } catch (e: Exception) {
            Log.e("LocationRepository", "位置情報の取得に失敗: ${e.message}")
            null
        }
    }
}

