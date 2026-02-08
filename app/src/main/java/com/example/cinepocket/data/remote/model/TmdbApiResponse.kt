package com.example.cinepocket.data.remote.model

/**
 * Respuesta de la API de TMDB para el endpoint de películas populares
 *
 * Encapsula la lista de resultados devuelta por la API
 *
 * @property results Lista de películas populares (vacía por defecto)
 */
data class TmdbApiResponse(
    val results: List<MovieResult> = emptyList()
)