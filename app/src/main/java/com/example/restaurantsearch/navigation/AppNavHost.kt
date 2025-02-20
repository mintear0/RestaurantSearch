package com.example.restaurantsearch.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.restaurantsearch.screens.ConditionScreen
import com.example.restaurantsearch.screens.DetailScreen
import com.example.restaurantsearch.screens.SearchingScreen
import com.example.restaurantsearch.screens.ResultScreen
import com.example.restaurantsearch.viewmodel.ConditionViewModel
import com.example.restaurantsearch.viewmodel.RestaurantViewModel
import com.example.restaurantsearch.viewmodel.SearchViewModel

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    val conditionViewModel: ConditionViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()
    val restaurantViewModel : RestaurantViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "condition",
        modifier = modifier.padding(paddingValues)
    ) {
        composable("condition") {
            ConditionScreen(modifier, navController, conditionViewModel)
        }
        composable("searching/{budget}/{range}",
            arguments = listOf(
                navArgument("budget") { type = NavType.StringType },
                navArgument("range") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val budget = backStackEntry.arguments?.getString("budget")?:""
            val range = backStackEntry.arguments?.getString("range")?:""

            SearchingScreen(modifier, navController, searchViewModel, restaurantViewModel, budget, range)
        }
        composable("result") {
            ResultScreen(modifier, navController, restaurantViewModel, searchViewModel, conditionViewModel.range)
        }
        composable("detail/{shopId}",
            arguments = listOf(
                navArgument("shopId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val shopId = backStackEntry.arguments?.getString("shopId")?:""
            DetailScreen(modifier, navController, restaurantViewModel, shopId)
        }
    }
}
