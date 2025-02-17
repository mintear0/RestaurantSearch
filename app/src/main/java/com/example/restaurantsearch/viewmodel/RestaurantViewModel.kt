package com.example.restaurantsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearch.deta.RestaurantRepository
import com.example.restaurantsearch.deta.SearchData
import com.example.restaurantsearch.deta.Shop
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val repository = RestaurantRepository.instance

    private val _restaurants = MutableLiveData<List<Shop>>()
    val restaurants: LiveData<List<Shop>> = _restaurants

    fun getRestaurants(searchData: SearchData) {
        viewModelScope.launch {
            val result = repository.getGourmet(
                searchData.key,
                searchData.feeCode,
                searchData.range.toString(),
                searchData.lat.toString(),
                searchData.lng.toString()
            )
            result.onSuccess {
                    response ->
                _restaurants.value = response.results.shop
                Log.d("RestaurantViewModel", "_restaurants.valueは{$_restaurants.value}だよ")
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }
}