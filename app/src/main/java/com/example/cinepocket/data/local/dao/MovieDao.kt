package com.example.cinepocket.data.local.dao

import androidx.room.*
import com.example.cinepocket.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operaciones BBDD movies
 *
 * Define operaciones CRUD para la tabla "movies" en Room.
 * Todas las funciones suspend se ejecutan en hilos de fondo automáticamente.
 */
@Dao
interface MovieDao
{

    /**
     * Observa todas las películas de la base de datos ordenadas por calificación.
     *
     * Este Flow emite automáticamente cuando hay cambios en la tabla.
     *
     * @return Flow que emite la lista actualizada de películas ordenadas por rating
     */
    @Query("SELECT * FROM movies ORDER BY rating DESC")
    fun observeMovies(): Flow<List<MovieEntity>>

    /**
     * Obtiene una película específica por su ID.
     *
     * @param id Identificador único de la película
     * @return La película si existe, null si no se encuentra
     */
    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): MovieEntity?

    /**
     * Inserta o actualiza lista de películas.
     *
     * Si una película con el mismo ID ya existe, la reemplaza completamente.
     *
     * @param movies Lista de películas a insertar o actualizar
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies: List<MovieEntity>)

    /**
     * Actualiza película existente.
     *
     * Room identifica la película a actualizar por clave primaria (id).
     *
     * @param movie Película con los datos actualizados
     */
    @Update
    suspend fun update(movie: MovieEntity)

    /**
     * Elimina todas las películas de la base de datos
     */
    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}