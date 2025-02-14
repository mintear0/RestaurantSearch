package com.example.restaurantsearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantsearch.screens.ConditionScreen
import com.example.restaurantsearch.screens.SearchingScreen
import com.example.restaurantsearch.screens.ResultScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "condition",
        modifier = modifier
    ) {
        composable("condition") {
            ConditionScreen(
                onContinueClicked = {
                    navController.navigate("searching") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        composable("searching") {
            SearchingScreen(navController)
        }
        composable("result") {
            ResultScreen()
        }
    }
}
