package com.linkdevelopment.android_tasks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linkdevelopment.android_tasks.data.local.entity.MovieLocalEntity


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(
        movies: List<MovieLocalEntity>
    )

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieLocalEntity>
    @Query("DELETE FROM movies")
    suspend fun deleteMovies()

}