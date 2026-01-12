package com.example.cinepocket


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cinepocket.navigation.AppNavHost
import com.example.cinepocket.ui.theme.CinePocketTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinePocketTheme {
                AppNavHost()
            }
        }
    }
}
