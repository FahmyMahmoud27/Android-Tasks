package com.linkdevelopment.android_tasks.data.remote.remotedatasource

import android.util.Log
import com.linkdevelopment.android_tasks.data.remote.MoviesApi
import com.linkdevelopment.android_tasks.data.remote.models.MoviesResponse
import com.linkdevelopment.android_tasks.data.remote.validate.IValidateRemoteDataSource
import com.linkdevelopment.android_tasks.domain.model.DataState
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi,
    private val validateRemoteDataSource: IValidateRemoteDataSource
) : IRemoteDataSource {
    override suspend fun getNowPlayingMovies(page: Int): DataState<MoviesResponse> {
        return try {
            val response = moviesApi.getNowPlayingMovies(page)
            validateRemoteDataSource.validate(response)
        } catch (e: Exception) {
            DataState.StateError(
                errorMessages = e.message ?: "Unknown error"
            )

        }
    }
}