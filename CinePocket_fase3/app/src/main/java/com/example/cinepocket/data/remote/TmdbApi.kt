package com.example.cinepocket.data.remote

import com.example.cinepocket.data.remote.dto.TmdbPopularResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/popular")
    suspend fun popular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TmdbPopularResponse
}
