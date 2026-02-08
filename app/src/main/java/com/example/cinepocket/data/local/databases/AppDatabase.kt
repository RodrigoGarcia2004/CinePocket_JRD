package com.example.cinepocket.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinepocket.data.local.dao.MovieDao
import com.example.cinepocket.data.local.entity.MovieEntity

/**
 * Contenedor de Room
 *
 * @property movieDao Acceso a operaciones de la tabla movies
 */
@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Proporciona acceso al DAO de películas.
     *
     * @return Instancia del MovieDao para realizar operaciones de base de datos
     */
    abstract fun movieDao(): MovieDao
}