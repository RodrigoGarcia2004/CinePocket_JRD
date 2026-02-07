package com.example.cinepocket

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cinepocket.navigation.AppNavHost
import com.example.cinepocket.ui.theme.CinePocketTheme
import dagger.hilt.android.AndroidEntryPoint  // ← AÑADIR
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint  // ← AÑADIR
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CinePocketTheme {
                AppNavHost()  // ← Sin parámetros
            }
        }
    }
}
@HiltAndroidApp
class CinePocketApp : Application()