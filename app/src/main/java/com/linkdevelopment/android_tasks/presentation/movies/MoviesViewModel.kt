package com.linkdevelopment.android_tasks.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkdevelopment.android_tasks.domain.model.DataState
import com.linkdevelopment.android_tasks.domain.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: IMoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesContract.UiState())

    val state = _state.asStateFlow()

    init {
        getGridSelected()
        getMovies()
    }

    private fun getGridSelected() {
        viewModelScope.launch {
            repository.getGridSelected().collect {
                _state.value = _state.value.copy(
                    isGrid = it
                )
            }
        }
    }

    fun getMovies(loadingType: LoadingType = LoadingType.NORMAL) {
        viewModelScope.launch {
            val page = _state.value.page
            _state.value = _state.value.copy(
                isLoading = loadingType == LoadingType.NORMAL,
                loadingType = loadingType,
                errorMessage = null
            )
            val result = repository.getMovies(page)
            when (result) {
                is DataState.StateSuccess -> {
                    _state.value = _state.value.copy(
                        movies = if (loadingType == LoadingType.PAGINATION) {
                            _state.value.movies + result.data.movies
                        } else {
                            result.data.movies
                        },
                        totalPages = result.data.totalPages,
                        isLoading = false,
                        loadingType = LoadingType.NONE
                    )
                }
                is DataState.StateError -> {
                    _state.value = _state.value.copy(
                        errorMessage = result.errorMessages,
                        isLoading = false,
                        loadingType = LoadingType.NONE
                    )
                }
            }
        }
    }

    private fun requestNextPage() {
        val currentState = _state.value
        if (
            currentState.page < currentState.totalPages &&
            currentState.loadingType == LoadingType.NONE
        ) {
            _state.value = currentState.copy(
                page = currentState.page + 1
            )
            getMovies(
                LoadingType.PAGINATION
            )
        }
    }

    fun onAction(action: MoviesContract.UiAction) {
        when (action) {
            MoviesContract.UiAction.ChangeToGrid -> {
                viewModelScope.launch {
                    repository.saveGridSelected(true)
                }
                _state.value = _state.value.copy(
                    isGrid = true
                )
            }
            MoviesContract.UiAction.ChangeToList -> {
                viewModelScope.launch {
                    repository.saveGridSelected(false)
                }
                _state.value = _state.value.copy(
                    isGrid = false
                )
            }
            MoviesContract.UiAction.LoadNextPage -> {
                requestNextPage()
            }
        }
    }
}