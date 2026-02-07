package com.example.cinepocket.data.di

import android.content.Context
import androidx.room.Room
import com.example.cinepocket.data.local.dao.MovieDao
import com.example.cinepocket.data.local.databases.AppDatabase
import com.example.cinepocket.data.remote.datasource.TmdbApi
import com.example.cinepocket.data.remote.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proporciona la base de datos
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies.db"
        ).build()
    }

    // Proporciona el DAO
    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao {
        return db.movieDao()
    }

    // Proporciona la API de TMDB
    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi {
        return RetrofitClient.api
    }

    // Proporciona la API Key
    @Provides
    fun provideApiKey(): String {
        return "6076f56522cee4d49e55497b978ae7c5"
    }
}