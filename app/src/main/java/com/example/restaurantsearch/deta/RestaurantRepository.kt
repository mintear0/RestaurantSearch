package com.example.restaurantsearch.deta

import com.example.restaurantsearch.api.ApiService
import com.example.restaurantsearch.api.RetrofitClient
import com.example.restaurantsearch.deta.ResponseAPI
import retrofit2.HttpException
import retrofit2.Response
import kotlin.Result

class RestaurantRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getGourmet(
        apiKey: String,
        budget: String,
        range: String,
        lat: String,
        lng: String
    ):Result<ResponseAPI> {
        return try {
            val response = apiService.getGourmet(apiKey, budget, range, lat, lng, "json")

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("レスポンスのボディが null"))
            } else {
                Result.failure(Exception("HTTPエラー: ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("HTTPエラー: ${e.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        val instance: RestaurantRepository by lazy { RestaurantRepository() }
    }

}