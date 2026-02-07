package com.example.cinepocket.data.remote.dto


data class TmdbPopularResponse(
    val results: List<TmdbMovieDto>
)

data class TmdbMovieDto(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)
