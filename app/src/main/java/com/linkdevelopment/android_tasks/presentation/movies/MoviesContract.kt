package com.linkdevelopment.android_tasks.presentation.movies

import com.linkdevelopment.android_tasks.domain.model.MoviesEntity

object MoviesContract {


    data class UiState(
        val movies: List<MoviesEntity> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isGrid: Boolean = false
    )

    sealed class UiAction{
        data object ChangeToGrid : UiAction()
        data object ChangeToList : UiAction()
    }
}