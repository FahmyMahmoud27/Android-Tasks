package com.linkdevelopment.android_tasks.data.repository

import com.linkdevelopment.android_tasks.data.local.LocalDataSource
import com.linkdevelopment.android_tasks.data.local.entity.MovieLocalEntity
import com.linkdevelopment.android_tasks.data.remote.remotedatasource.IRemoteDataSource
import com.linkdevelopment.android_tasks.domain.model.DataState
import com.linkdevelopment.android_tasks.domain.model.MoviesEntity
import com.linkdevelopment.android_tasks.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMoviesRepository {

    override suspend fun getMovies(): DataState<List<MoviesEntity>> {

        val response = remoteDataSource.getNowPlayingMovies()

        return when (response) {
            is DataState.StateSuccess -> {
                val moviesLocal = response.data.movies?.map {
                    MovieLocalEntity(
                        id = it?.id ?: 0,
                        title = it?.title.orEmpty(),
                        posterPath = "https://image.tmdb.org/t/p/w500${it?.posterPath.orEmpty()}"
                    )
                } ?: emptyList()

                localDataSource.saveMovies(moviesLocal)
                val moviesEntity = moviesLocal.map {
                    MoviesEntity(
                        id = it.id,
                        title = it.title,
                        posterPath = it.posterPath
                    )
                }
                DataState.StateSuccess(
                    moviesEntity
                )
            }
            is DataState.StateError -> {
                val moviesLocal = localDataSource.getMovies()
                if (moviesLocal.isNotEmpty()) {
                    val moviesEntity = moviesLocal.map {
                        MoviesEntity(
                            id = it.id,
                            title = it.title,
                            posterPath = it.posterPath
                        )
                    }
                    DataState.StateSuccess(
                        moviesEntity
                    )
                } else {
                    response
                }
            }
        }
    }

    override suspend fun saveGridSelected(isGrid: Boolean) {
        localDataSource.saveIsGridSelected(isGrid)
    }
    override fun getGridSelected(): Flow<Boolean> {
        return localDataSource.getGridSelected()
    }
}