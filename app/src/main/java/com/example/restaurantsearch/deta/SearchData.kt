package com.example.restaurantsearch.deta

import java.io.Serializable

// APIの検索データを保存するdata class
data class SearchData(
    var key: String,              // APIキー
    var feeCode: String,       // 予算コード
    var range: Int,          // 検索範囲
    var lat: Double,              // 緯度
    var lng: Double,              // 経度
    var format: String,        // json形式
): Serializable
