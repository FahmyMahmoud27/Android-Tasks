package com.linkdevelopment.android_tasks.data.remote.validate

import com.linkdevelopment.android_tasks.domain.model.DataState
import retrofit2.Response
import javax.inject.Inject


class ValidateRemoteDataSource @Inject constructor() : IValidateRemoteDataSource {
    override suspend fun <T> validate(response: Response<T>): DataState<T> {
        return try {
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    DataState.StateSuccess(data = body)
                else
                    DataState.StateError("Empty response body")
            } else
                DataState.StateError(
                    "Something went wrong: ${response.code()}"
                )
        } catch (e: Exception) {
            DataState.StateError(
                e.message ?: "Unknown error"
            )
        }
    }


}