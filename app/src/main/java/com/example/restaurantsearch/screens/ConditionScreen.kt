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
import androidx.navigation.NavController
import com.example.restaurantsearch.viewmodel.ConditionViewModel

@Composable
fun ConditionScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: ConditionViewModel = viewModel()
    var showError by remember { mutableStateOf(false) } // エラー表示フラグ

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("検索範囲と金額を指定")

        InputRange(viewModel, showError)
        InputBudget(viewModel, showError)

        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                if (viewModel.range.isEmpty() || viewModel.budget.isEmpty()) {
                    showError = true // 入力が空ならエラー表示
                } else {
                    showError = false
                    navController.navigate("searching/${viewModel.range}/${viewModel.budget}")
                }
            }
        ) {
            Text("お店を検索！")
        }
    }
}

@Composable
fun InputRange(viewModel: ConditionViewModel, showError: Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 6.dp),
        value = viewModel.range,
        onValueChange = { viewModel.updateDistance(it) },
        label = { Text("検索範囲を入力(m)") },
        isError = showError && viewModel.range.isEmpty(),
        supportingText = {
            if (showError && viewModel.range.isEmpty()) {
                Text("入力してください", color = Color.Red)
            }
        }
    )
}

@Composable
fun InputBudget(viewModel: ConditionViewModel, showError: Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 6.dp),
        value = viewModel.budget,
        onValueChange = { viewModel.updateMoney(it) },
        label = { Text("金額を入力(円)") },
        isError = showError && viewModel.budget.isEmpty(),
        supportingText = {
            if (showError && viewModel.budget.isEmpty()) {
                Text("入力してください", color = Color.Red)
            }
        }
    )
}