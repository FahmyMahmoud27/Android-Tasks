package com.linkdevelopment.android_tasks.presentation.movies

import com.linkdevelopment.android_tasks.domain.model.MoviesEntity

object MoviesContract {


    data class UiState(
        val movies: List<MoviesEntity> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isGrid: Boolean = false,
        val page: Int = 1,
        val totalPages: Int = 0,
        val loadingType: LoadingType = LoadingType.NONE
    )

    sealed class UiAction{
        data object ChangeToGrid : UiAction()
        data object ChangeToList : UiAction()

        data object LoadNextPage : UiAction()
    }
}

enum class LoadingType {
    NONE,
    NORMAL,
    PAGINATION
}