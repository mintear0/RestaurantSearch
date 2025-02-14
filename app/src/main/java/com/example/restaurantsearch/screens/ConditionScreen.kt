package com.example.restaurantsearch.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantsearch.viewmodel.SearchViewModel

@Composable
fun ConditionScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: SearchViewModel = viewModel()
    var showError by remember { mutableStateOf(false) } // エラー表示フラグ

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("検索範囲と金額を指定")

        InputDistance(viewModel, showError)
        InputMoney(viewModel, showError)

        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                if (viewModel.distance.isEmpty() || viewModel.money.isEmpty()) {
                    showError = true // 入力が空ならエラー表示
                } else {
                    showError = false
                    onContinueClicked()
                }
            }
        ) {
            Text("お店を検索！")
        }
    }
}

@Composable
fun InputDistance(viewModel: SearchViewModel, showError: Boolean) {
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
fun InputMoney(viewModel: SearchViewModel, showError: Boolean) {
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