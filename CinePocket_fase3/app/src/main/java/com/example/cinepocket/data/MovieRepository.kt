package com.example.cinepocket.data


import com.example.cinepocket.data.local.MovieDao
import com.example.cinepocket.data.local.MovieEntity
import com.example.cinepocket.data.remote.TmdbApi
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val api: TmdbApi,
    private val dao: MovieDao,
    private val apiKey: String
) {
    fun observeMovies(): Flow<List<MovieEntity>> = dao.observeMovies()

    suspend fun importPopular() {
        val response = api.popular(apiKey = apiKey, page = 1)
        val list = response.results.map { dto ->
            MovieEntity(
                id = dto.id,
                title = dto.title,
                overview = dto.overview.orEmpty(),
                posterPath = dto.poster_path,
                releaseDate = dto.release_date,
                rating = dto.vote_average ?: 0.0
            )
        }
        dao.upsertAll(list)
    }

    suspend fun toggleFavorite(id: Int) {
        val movie = dao.getById(id) ?: return
        dao.update(movie.copy(isFavorite = !movie.isFavorite))
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
