package com.example.restaurantsearch.deta

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.tasks.await

class LocationRepository(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun fetchLocation(): Pair<Double, Double>? {
        return try {
            /*
            // まず、キャッシュされた位置を取得
            val lastKnownLocation = fusedLocationClient.lastLocation.await()
            lastKnownLocation?.let {
                return Pair(it.latitude, it.longitude)
             */
                val priority = Priority.PRIORITY_HIGH_ACCURACY
                val cancellationTokenSource = CancellationTokenSource()
                val newLocation = fusedLocationClient.getCurrentLocation(priority, cancellationTokenSource.token).await()

                newLocation?.let { Pair(it.latitude, it.longitude) }

            // キャッシュがない場合は、新しい位置情報を取得

        } catch (e: Exception) {
            Log.e("LocationRepository", "位置情報の取得に失敗: ${e.message}")
            null
        }
    }
}

