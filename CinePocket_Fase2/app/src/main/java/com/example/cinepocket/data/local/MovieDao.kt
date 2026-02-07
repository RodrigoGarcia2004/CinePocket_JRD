package com.example.cinepocket.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY rating DESC")
    fun observeMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
