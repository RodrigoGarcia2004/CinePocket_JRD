package com.example.cinepocket.data.remote.model

data class MovieResult(
    val id: Int,
    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)
