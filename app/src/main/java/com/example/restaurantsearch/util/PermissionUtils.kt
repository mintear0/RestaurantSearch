package com.example.restaurantsearch.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel

/**
 * 位置情報のパーミッションをリクエストする関数
 */
fun requestLocationPermission(
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    onPermissionGranted: () -> Unit
) {
    when {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            // 既に許可されている場合
            onPermissionGranted()
        }
        else -> {
            // 許可をリクエスト
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}
