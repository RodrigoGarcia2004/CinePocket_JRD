package com.example.cinepocket.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinepocket.data.local.entity.MovieEntity
import com.example.cinepocket.data.local.dao.MovieDao

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun movieDao(): MovieDao
}