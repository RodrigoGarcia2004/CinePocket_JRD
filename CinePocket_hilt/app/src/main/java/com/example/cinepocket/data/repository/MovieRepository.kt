package com.example.cinepocket.data.repository

import com.example.cinepocket.data.local.dao.MovieDao
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.data.remote.datasource.TmdbApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dataSource: TmdbApi,
    private val movieDao: MovieDao,
    private val apiKey: String
) {

    fun observeMovies(): Flow<List<MovieEntity>> {
        return movieDao.observeMovies()
    }

    // ✅ AÑADIR ESTA FUNCIÓN
    suspend fun getMovieById(id: Int): MovieEntity? {
        return movieDao.getById(id)
    }

    suspend fun getPopularMovies(): List<MovieEntity> {
        val response = dataSource.getPopularMovies(apiKey = apiKey, page = 1)

        return response.results.map { result ->
            MovieEntity(
                id = result.id,
                title = result.title,
                overview = result.overview.orEmpty(),
                posterPath = result.poster_path,
                releaseDate = result.release_date,
                rating = result.vote_average ?: 0.0
            )
        }
    }

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.upsertAll(movies)
    }

    suspend fun toggleFavorite(id: Int) {
        val movie = movieDao.getById(id) ?: return
        movieDao.update(movie.copy(isFavorite = !movie.isFavorite))
    }

    suspend fun deleteAll() {
        movieDao.deleteAll()
    }
}