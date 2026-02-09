package com.example.cinepocket.data.remote.retrofit

import com.example.cinepocket.data.remote.datasource.TmdbApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente Retrofit para la API de TMDB
 *
 * Configura y proporciona una instancia única del servicio [TmdbApi]
 * con logging básico para depuración.
 */
object RetrofitClient
{
    /**
     * URL base de la API de TMDB
     */
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    /**
     * Interceptor para logging de peticiones HTTP
     */
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    /**
     * Cliente HTTP configurado con logging
     */
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    /**
     * Instancia del servicio [TmdbApi] configurado con Retrofit
     */
    val api: TmdbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }
}