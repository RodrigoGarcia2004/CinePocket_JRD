package com.example.cinepocket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinepocket.ui.viewmodel.HomeViewModel
import com.example.cinepocket.ui.screens.DetailScreen
import com.example.cinepocket.ui.screens.HomeScreen
import com.example.cinepocket.ui.screens.LoginScreen

@Composable
fun AppNavHost(homeVm: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                vm = homeVm,
                onMovieClick = { movieId ->
                    navController.navigate("${Routes.DETAIL}/$movieId")
                }
            )
        }

        composable(
            route = "${Routes.DETAIL}/{${Routes.MOVIE_ID_ARG}}",
            arguments = listOf(
                navArgument(Routes.MOVIE_ID_ARG) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(Routes.MOVIE_ID_ARG) ?: -1
            DetailScreen(
                movieId = movieId,
                vm = homeVm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
