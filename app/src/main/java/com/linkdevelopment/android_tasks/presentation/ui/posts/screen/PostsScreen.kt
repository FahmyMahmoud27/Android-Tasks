package com.linkdevelopment.android_tasks.presentation.ui.posts.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.linkdevelopment.android_tasks.domain.model.Post
import com.linkdevelopment.android_tasks.presentation.ui.posts.contract.PostsContract
import com.linkdevelopment.android_tasks.presentation.ui.posts.viewmodel.PostsViewModel
import com.linkdevelopment.android_tasks.presentation.ui.utils.PostItem

@Composable
fun PostsScreen(
    viewModel: PostsViewModel = hiltViewModel(),
    onNavigateToDetails: (Post) -> Unit
) {
    val state by viewModel.postsState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.sendNewEvent(PostsContract.PostsEvents.OnPageOpened)
    }

    LaunchedEffect(viewModel) {
        viewModel.uiActions.collect { action ->

            when (action) {

                is PostsContract.OneTimeActions.NavigateToDetails -> {
                    onNavigateToDetails(action.post)
                }

            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(
                    text = state.error ?: ""
                )
            }

            else -> {
                LazyColumn {
                    items(state.posts) { post ->

                        PostItem(
                            post = post,
                            onClick = {
                                viewModel.sendNewEvent(PostsContract.PostsEvents.OnPostClicked(post))
                            }
                        )

                    }
                }
            }
        }
    }


}