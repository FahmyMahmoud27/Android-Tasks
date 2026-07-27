package com.linkdevelopment.android_tasks.presentation.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.TableInfo
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    LaunchedEffect(listState, state.movies.size) {

        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .distinctUntilChanged()
            .collect { lastVisibleItem ->

                if (lastVisibleItem != null &&
                    lastVisibleItem >= state.movies.size - 2
                ) {

                    viewModel.onAction(
                        MoviesContract.UiAction.LoadNextPage
                    )

                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    viewModel.onAction(
                        MoviesContract.UiAction.ChangeToGrid
                    )
                }
            ) {
                Text("Grid")
            }
            Button(
                onClick = {
                    viewModel.onAction(
                        MoviesContract.UiAction.ChangeToList
                    )
                }
            ) {
                Text("List")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (state.isLoading) {

                CircularProgressIndicator()

            } else if (state.errorMessage != null) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No Internet Connection",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        onClick = {
                            viewModel.getMovies()
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Retry"
                        )
                    }
                }

            } else {

                if (state.isGrid) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.movies) { movie ->
                            MovieCard(
                                movie = movie
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        state = listState
                    ) {
                        items(state.movies) { movie ->
                            MovieCard(
                                movie = movie
                            )
                        }
                        if (state.loadingType == LoadingType.PAGINATION) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }

            }

        }

    }
}