package com.linkdevelopment.android_tasks.data.remote.validate

import com.linkdevelopment.android_tasks.domain.model.DataState
import retrofit2.Response

interface IValidateRemoteDataSource {
    suspend fun <T> validate(response: Response<T>): DataState<T>
}