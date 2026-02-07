package com.example.cinepocket.data.remote.model

data class TmdbApiResponse(
    val results: List<MovieResult> = emptyList()
)
