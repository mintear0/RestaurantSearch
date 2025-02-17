package com.example.restaurantsearch.api

import com.example.restaurantsearch.deta.ResponseAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("gourmet/v1/")
    suspend fun getGourmet(
        @Query("key") key: String,              // APIキー
        @Query("budget") feeCode: String,       // 予算コード
        @Query("range") range: String,          // 検索範囲
        @Query("lat") lat: String,              // 緯度
        @Query("lng") lng: String,              // 経度
        @Query("format") format: String,        // json形式
    ): Response<ResponseAPI>
}