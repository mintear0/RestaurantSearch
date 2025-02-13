package com.example.restaurantsearch

import android.content.res.Resources.Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    var distance by mutableStateOf("")
        private set
    fun updateDistance(input: String) {
        if(input.all { it.isDigit() }) {
            distance = input
        }
    }

    var money by mutableStateOf("")
        private set
    fun updateMoney(input: String) {
        if(input.all { it.isDigit() }) {
            money = input
        }
    }

    var isCompleted by mutableStateOf(false)
        private set
    fun SearchingRestaurant() {
        viewModelScope.launch {
            // 何かの処理 (APIリクエストなど)
            delay(3000) // 例: 3秒待機 (本来はAPIレスポンスを待つ)
            isCompleted = true
        }
    }
}