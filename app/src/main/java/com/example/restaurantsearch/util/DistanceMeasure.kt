package com.example.restaurantsearch.util

import android.util.Log
import com.example.restaurantsearch.deta.Shop
import com.example.restaurantsearch.viewmodel.SearchViewModel
import java.lang.Math.pow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object DistanceMeasure {
    // 弧度法へ変換
    private fun deg2rad(deg: Double?):Double {
        if (deg != null) {
            Log.d("deg2rad", "変換できました")
            return deg * PI / 180.0
        } else {
            Log.e("deg2rad", "変換できませんでした")
            return 0.0
        }
    }

    // Hubenyの公式を使って2点間の距離を取得する
    private fun calDistance(lat: Double, lng: Double, latnow: Double, lngnow: Double):Double {
        val RX = 6378.137                                                                     // 回転楕円体の長半径（赤道半径）[km]
        val RY = 6356.752                                                                     // 回転楕円体の短半径（極半径）[km]
        val dlat = lat - latnow
        val dlng = lng - lngnow
        val mu = (lat + latnow) / 2.0                                                      // μ
        val E = sqrt(1 - (RY / RX).pow(2.0))                                            // 離心率
        val W = sqrt(1 - (E * sin(mu)).pow(2.0))
        val M = RX * (1 - E.pow(2.0)) / W.pow(3.0)                                      // 子午線曲率半径
        val N = RX / W                                                                        // 卯酉線曲率半径

        Log.d("calDistance", "$dlat\n$dlng\n$mu\n$E\n$W\n$M\n$N\n")
        return sqrt((M * dlat).pow(2.0) + (N * dlng * cos(mu)).pow(2.0)) * 1000      // 距離[m]
    }

    private fun  distanceMeasure(shop: Shop, searchViewModel: SearchViewModel):Int {
        // 弧度法へ変換
        val lat = deg2rad(shop.lat?.toDouble())
        val lng = deg2rad(shop.lng?.toDouble())
        val latnow = deg2rad(searchViewModel.locationData.value?.first)
        val lngnow = deg2rad(searchViewModel.locationData.value?.second)
        val a = searchViewModel.locationData.value?.first
        val b = searchViewModel.locationData.value?.second

        Log.d("distanceMeasure", "$a\n$b")
        Log.d("distanceMeasure", calDistance(lat, lng, latnow, lngnow).toInt().toString())
        return calDistance(lat, lng, latnow, lngnow).toInt()
    }

    fun distanceFilter(shop: Shop, range: String, searchViewModel: SearchViewModel):Boolean {
        val distance = distanceMeasure(shop, searchViewModel)
        Log.d("distanceFilter", (range.toInt() >= distance).toString())
        return (range.toInt() >= distance)
    }


}