package com.example.cinepocket.data.remote.model

/**
 * Modelo de datos que representa una película en la respuesta de la API de TMDB
 *
 * Esta clase mapea directamente la estructura JSON de TMDB
 *
 * @property id Identificador único de la película en TMDB
 * @property title Título de la película
 * @property overview Sinopsis (puede ser null si no está disponible)
 * @property poster_path Ruta del póster relativa a la URL base de imágenes de TMDB
 * @property release_date Fecha de estreno en formato YYYY-MM-DD
 * @property vote_average Calificación promedio (0.0-10.0)
 */
data class MovieResult(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)