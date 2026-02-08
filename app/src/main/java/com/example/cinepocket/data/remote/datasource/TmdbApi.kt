package com.example.cinepocket.data.remote.datasource

import com.example.cinepocket.data.remote.model.TmdbApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz de servicio API para TMDB
 *
 * Define los endpoints disponibles para consultar información de películas
 * Retrofit genera automáticamente la implementación en tiempo de ejecución
 */
interface TmdbApi
{

    /**
     * Obtiene las películas populares de TMDB.
     *
     * Endpoint: GET /movie/popular
     *
     * @param apiKey Clave de API de TMDB para autenticación
     * @param page Número de página para paginación (cada página contiene ~20 películas)
     * @return Respuesta con lista de películas populares
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TmdbApiResponse
}