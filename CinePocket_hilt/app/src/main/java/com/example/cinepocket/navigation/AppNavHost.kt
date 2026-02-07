package com.example.cinepocket.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel  // ← AÑADIR
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinepocket.ui.screens.DetailScreen
import com.example.cinepocket.ui.screens.HomeScreen
import com.example.cinepocket.ui.screens.LoginScreen
import com.example.cinepocket.ui.viewmodel.HomeViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home")
                }
            )
        }

        composable("home") {
            val homeVm: HomeViewModel = hiltViewModel()  // ← Hilt crea el VM
            HomeScreen(
                vm = homeVm,
                onMovieClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable("detail/{movieId}") { backStackEntry ->
            val homeVm: HomeViewModel = hiltViewModel()  // ← Mismo VM compartido
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            DetailScreen(
                movieId = movieId,
                vm = homeVm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}