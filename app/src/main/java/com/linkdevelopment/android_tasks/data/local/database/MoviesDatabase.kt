package com.linkdevelopment.android_tasks.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.linkdevelopment.android_tasks.data.local.dao.MoviesDao
import com.linkdevelopment.android_tasks.data.local.entity.MovieLocalEntity


@Database(
    entities = [MovieLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}