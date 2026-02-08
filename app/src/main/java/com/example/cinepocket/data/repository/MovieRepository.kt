package com.example.cinepocket.data.repository

import com.example.cinepocket.data.local.dao.MovieDao
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.data.remote.datasource.TmdbApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repositorio para gestionar datos de películas
 *
 * Puente entre:
 * - Base de datos local (Room)
 * - API remota (TMDB)
 *
 * @property dataSource Cliente API de TMDB
 * @property movieDao DAO para acceso a base de datos local
 * @property apiKey Clave de autenticación de TMDB
 */
class MovieRepository @Inject constructor(
    private val dataSource: TmdbApi,
    private val movieDao: MovieDao,
    private val apiKey: String
)
{
    /**
     * Observa todas las películas almacenadas localmente
     *
     * @return Flow reactivo que emite la lista actualizada de películas
     */
    fun observeMovies(): Flow<List<MovieEntity>> {
        return movieDao.observeMovies()
    }

    /**
     * Obtiene una película específica por su ID desde la base de datos local
     *
     * @param id Identificador de la película
     * @return La película si existe, null si no se encuentra
     */
    suspend fun getMovieById(id: Int): MovieEntity? {
        return movieDao.getById(id)
    }

    /**
     * Obtiene películas populares desde la API de TMDB.
     *
     * Convierte los DTOs de la API (MovieResult) a entidades locales (MovieEntity)
     * No guarda automáticamente en la base de datos.
     *
     * @param page Número de página a consultar (1 por defecto)
     * @return Lista de películas convertidas a MovieEntity
     * @throws Exception Si la petición HTTP falla
     */
    suspend fun getPopularMovies(page: Int = 1): List<MovieEntity> {
        val response = dataSource.getPopularMovies(apiKey = apiKey, page = page)

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

    /**
     * Inserta o actualiza múltiples películas en la base de datos local.
     *
     * Si una película ya existe (mismo ID), la reemplaza completamente.
     *
     * @param movies Lista de películas a guardar
     */
    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.upsertAll(movies)
    }

    /**
     * Alterna el estado de favorito de una película.
     *
     * Si la película está marcada como favorita, la desmarca y viceversa.
     * Si la película no existe, no hace nada.
     *
     * @param id Identificador de la película
     */
    suspend fun toggleFavorite(id: Int) {
        val movie = movieDao.getById(id) ?: return
        movieDao.update(movie.copy(isFavorite = !movie.isFavorite))
    }

    /**
     * Elimina todas las películas de la base de datos local.
     */
    suspend fun deleteAll() {
        movieDao.deleteAll()
    }
}