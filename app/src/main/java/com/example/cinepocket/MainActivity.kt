package com.example.cinepocket

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cinepocket.navigation.AppNavHost
import com.example.cinepocket.ui.theme.CinePocketTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase de aplicación que inicializa Hilt.
 *
 * Hilt la usa para preparar todas las dependencias (DB, API, Repository)
 * antes de que las pantallas las necesiten.
 *
 */
@HiltAndroidApp
class CinePocketApp : Application()

/**
 * Pantalla principal de la aplicación.
 *
 * Es la primera Activity que se abre cuando el usuario pulsa el icono.
 * Hilt inyecta automáticamente las dependencias necesarias gracias a [@AndroidEntryPoint].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CinePocketTheme {
                AppNavHost()  // Sistema de navegación con Compose
            }
        }
    }
}

