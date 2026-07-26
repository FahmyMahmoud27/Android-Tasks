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
            repository.getGridSelected()
                .collect {
                    _state.value = _state.value.copy(
                        isGrid = it
                    )
                }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                errorMessage = null
            )
            val result = repository.getMovies()
            when (result) {
                is DataState.StateSuccess -> {
                    _state.value = _state.value.copy(
                        movies = result.data,
                        isLoading = false
                    )
                }

                is DataState.StateError -> {
                    _state.value = _state.value.copy(
                        errorMessage = result.errorMessages,
                        isLoading = false
                    )
                }
            }
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
        }
    }


}


