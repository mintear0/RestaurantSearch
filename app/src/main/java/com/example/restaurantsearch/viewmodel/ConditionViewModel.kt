package com.example.restaurantsearch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ConditionViewModel : ViewModel(){
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
}