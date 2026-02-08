package com.example.cinepocket.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una película en la base de datos local.
 *
 * Esta clase define la estructura de la tabla "movies" en Room.
 * Almacena información básica de películas obtenidas de TMDB API.
 *
 * @property id Identificador único de la película (proviene de TMDB)
 * @property title Título de la película
 * @property overview Sinopsis o descripción de la película
 * @property posterPath Ruta relativa del póster en TMDB (null si no tiene)
 * @property releaseDate Fecha de estreno en formato YYYY-MM-DD (null si desconocida)
 * @property rating Calificación promedio de la película (0.0-10.0)
 * @property isFavorite Indica si el usuario marcó esta película como favorita (pd false)
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val rating: Double,
    val isFavorite: Boolean = false
)