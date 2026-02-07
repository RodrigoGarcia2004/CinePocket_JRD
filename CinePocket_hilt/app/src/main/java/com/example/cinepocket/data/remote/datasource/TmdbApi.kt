package com.example.cinepocket.data.remote.datasource

import com.example.cinepocket.data.remote.model.TmdbApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TmdbApiResponse
}