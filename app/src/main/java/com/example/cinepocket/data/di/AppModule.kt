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

/**
 * Módulo de inyección de dependencias de Hilt.
 *
 * Define cómo crear las dependencias principales de la app:
 * - Base de datos Room
 * - DAO de películas
 * - Cliente API de TMDB
 * - Clave de API
 *
 * Hilt usa estas funciones para crear automáticamente las instancias
 * y pasarlas donde se necesiten (Repository, ViewModel, etc.).
 *
 * @Module: Le dice a Hilt que esta clase proporciona dependencias
 * @InstallIn(SingletonComponent::class): Las dependencias viven durante
 * toda la vida de la aplicación
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Proporciona la base de datos Room.
     *
     * Se crea solo UNA vez (@Singleton) y se reutiliza en toda la app.
     * Hilt inyecta automáticamente el Context de la aplicación.
     *
     * @param context Contexto de la aplicación (inyectado por Hilt)
     * @return Instancia única de AppDatabase
     */
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

    /**
     * Proporciona el DAO de películas.
     *
     * Hilt detecta que necesita AppDatabase, llama a provideDatabase()
     * automáticamente y se lo pasa como parámetro.
     *
     * @param db Base de datos de la app (inyectada automáticamente)
     * @return DAO para operaciones con películas
     */
    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao {
        return db.movieDao()
    }

    /**
     * Proporciona el cliente de la API de TMDB.
     *
     * Usa RetrofitClient para crear la implementación de la interfaz.
     * Se crea solo una vez (@Singleton).
     *
     * @return Instancia de TmdbApi configurada con Retrofit
     */
    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi {
        return RetrofitClient.api
    }

    /**
     * Proporciona la clave de API de TMDB.
     *
     * Esta clave se inyecta en el Repository para autenticar
     * las peticiones a la API.
     *
     * ⚠️ En producción, esta clave debería estar en local.properties
     * o en variables de entorno, no en el código.
     *
     * @return Clave de API de TMDB
     */
    @Provides
    fun provideApiKey(): String {
        return "6076f56522cee4d49e55497b978ae7c5"
    }
}