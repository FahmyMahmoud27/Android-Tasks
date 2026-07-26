package com.linkdevelopment.android_tasks.presentation.ui.posts.contract

import com.linkdevelopment.android_tasks.domain.model.Post

class PostsContract {

    data class PostsState(
        val isLoading: Boolean = false,
        val posts: List<Post> = emptyList(),
        val error: String? = null
    )

    sealed class PostsEvents {
        data object OnPageOpened : PostsEvents()
        data class OnPostClicked(val post: Post) : PostsEvents()
    }

    sealed class OneTimeActions {
        data class NavigateToDetails(val post: Post) : OneTimeActions()
    }
}