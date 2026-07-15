package com.linkdevelopment.android_tasks.presentation.ui.posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkdevelopment.android_tasks.domain.model.Post
import com.linkdevelopment.android_tasks.domain.usecase.GetPostsUseCase
import com.linkdevelopment.android_tasks.presentation.ui.posts.contract.PostsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _postsState = MutableStateFlow(PostsContract.PostsState())
    val postsState = _postsState.asStateFlow()

    private val _uiActions = Channel<PostsContract.OneTimeActions>()
    val uiActions = _uiActions.receiveAsFlow()

    private val _events = MutableSharedFlow<PostsContract.PostsEvents>()

    fun sendNewEvent(event: PostsContract.PostsEvents) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }

    init {
        collectEvents()
    }

    private fun collectEvents() {
        viewModelScope.launch {
            _events.collect { events ->
                onEvent(events)
            }
        }
    }

    private fun onEvent(event: PostsContract.PostsEvents) {
        when (event) {
            PostsContract.PostsEvents.OnPageOpened -> {
                getPosts()
            }

            is PostsContract.PostsEvents.OnPostClicked -> {
                navigateToDetails(event.post)
            }

        }

    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _postsState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val posts = getPostsUseCase()

                _postsState.update {
                    it.copy(
                        isLoading = false,
                        posts = posts
                    )
                }
            } catch (e: Exception) {
                _postsState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }

        }
    }

    private fun navigateToDetails(post: Post) {
        viewModelScope.launch {
            _uiActions.send(
                PostsContract.OneTimeActions.NavigateToDetails(post)
            )
        }
    }


}