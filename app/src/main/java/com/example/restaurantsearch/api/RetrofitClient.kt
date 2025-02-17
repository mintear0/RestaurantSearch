package com.example.restaurantsearch.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private const val BASE_URL = "http://webservice.recruit.co.jp/hotpepper/"

    val client = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
        .connectTimeout(30, TimeUnit.SECONDS)  // 接続タイムアウト
        .readTimeout(30, TimeUnit.SECONDS)     // 読み取りタイムアウト
        .build()

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson)) // JSON変換
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}