package com.example.cinepocket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.cinepocket.data.MovieRepository
import com.example.cinepocket.data.local.AppDatabase
import com.example.cinepocket.data.remote.RetrofitClient
import com.example.cinepocket.navigation.AppNavHost
import com.example.cinepocket.ui.viewmodel.HomeViewModel
import com.example.cinepocket.ui.theme.CinePocketTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movies.db"
        ).build()

        val repo = MovieRepository(
            api = RetrofitClient.api,
            dao = db.movieDao(),
            apiKey = "6076f56522cee4d49e55497b978ae7c5"
        )

        val homeVm = HomeViewModel(repo)

        setContent {
            CinePocketTheme {
                AppNavHost(homeVm = homeVm)
            }
        }
    }
}
