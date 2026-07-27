package com.linkdevelopment.android_tasks.data.remote.remotedatasource

import com.linkdevelopment.android_tasks.data.remote.models.MoviesResponse
import com.linkdevelopment.android_tasks.domain.model.DataState

interface IRemoteDataSource {

    suspend fun getNowPlayingMovies(page: Int): DataState<MoviesResponse>

}