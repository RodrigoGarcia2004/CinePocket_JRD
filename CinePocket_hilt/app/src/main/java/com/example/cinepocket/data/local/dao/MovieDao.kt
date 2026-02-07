package com.example.cinepocket.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cinepocket.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY rating DESC")
    fun observeMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}