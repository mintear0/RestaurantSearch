package com.example.restaurantsearch.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restaurantsearch.viewmodel.ConditionViewModel

@Composable
fun InputDistance(viewModel: ConditionViewModel, showError: Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 6.dp),
        value = viewModel.distance,
        onValueChange = { viewModel.updateDistance(it) },
        label = { Text("検索範囲を入力(m)") },
        isError = showError && viewModel.distance.isEmpty(),
        supportingText = {
            if (showError && viewModel.distance.isEmpty()) {
                Text("入力してください", color = Color.Red)
            }
        }
    )
}

@Composable
fun InputMoney(viewModel: ConditionViewModel, showError: Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 12.dp),
        value = viewModel.money,
        onValueChange = { viewModel.updateMoney(it) },
        label = { Text("金額を入力(円)") },
        isError = showError && viewModel.money.isEmpty(),
        supportingText = {
            if (showError && viewModel.money.isEmpty()) {
                Text("入力してください", color = Color.Red)
            }
        }
    )
}