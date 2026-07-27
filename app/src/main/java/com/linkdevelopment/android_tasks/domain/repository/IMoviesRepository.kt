package com.linkdevelopment.android_tasks.domain.repository

import com.linkdevelopment.android_tasks.domain.model.DataState
import com.linkdevelopment.android_tasks.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    suspend fun getMovies(page: Int): DataState<MoviesModel>

    suspend fun saveGridSelected(isGrid: Boolean)

    fun getGridSelected(): Flow<Boolean>
}