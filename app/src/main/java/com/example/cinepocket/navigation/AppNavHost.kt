package com.example.cinepocket.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinepocket.ui.screens.DetailScreen
import com.example.cinepocket.ui.screens.FavoritesScreen
import com.example.cinepocket.ui.screens.HomeScreen
import com.example.cinepocket.ui.screens.LoginScreen
import com.example.cinepocket.ui.viewmodel.HomeViewModel

/**
 * Define la navegación principal de la app.
 *
 * Aquí se configuran todas las pantallas y cómo se navega entre ellas
 * usando Navigation Compose.
 */
@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME)
                }
            )
        }

        composable(Routes.HOME) {
            val homeVm: HomeViewModel = hiltViewModel()
            HomeScreen(
                vm = homeVm,
                onMovieClick = { id ->
                    navController.navigate(Routes.detail(id))
                },
                onFavoritesClick = {
                    navController.navigate(Routes.FAVORITES)
                }
            )
        }

        composable(Routes.FAVORITES) {
            val homeVm: HomeViewModel = hiltViewModel()
            FavoritesScreen(
                vm = homeVm,
                onMovieClick = { id ->
                    navController.navigate(Routes.detail(id))
                },
                onBack = {navController.popBackStack()}
            )
        }

        composable(Routes.DETAIL) { backStackEntry ->
            val homeVm: HomeViewModel = hiltViewModel()
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            DetailScreen(
                movieId = movieId,
                vm = homeVm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}