package com.linkdevelopment.android_tasks.domain.model

sealed class DataState<out T> {
    data class StateSuccess<out T>(val data: T) : DataState<T>()

    data class StateError(val errorMessages: String): DataState<Nothing>()
}