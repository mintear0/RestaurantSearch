package com.example.restaurantsearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.restaurantsearch.screens.ConditionScreen
import com.example.restaurantsearch.screens.SearchingScreen
import com.example.restaurantsearch.screens.ResultScreen
import com.example.restaurantsearch.viewmodel.RestaurantViewModel
import com.example.restaurantsearch.viewmodel.SearchViewModel

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val searchViewModel: SearchViewModel = viewModel()
    val restaurantViewModel : RestaurantViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "condition",
        modifier = modifier
    ) {
        composable("condition") {
            ConditionScreen(modifier, navController)
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
            ResultScreen(modifier, restaurantViewModel)
        }
    }
}
