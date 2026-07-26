package com.linkdevelopment.android_tasks.presentation.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.linkdevelopment.android_tasks.domain.model.MoviesEntity


@Composable
fun MovieCard(
    movie: MoviesEntity
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )


            Text(
                text = movie.title,
                modifier = Modifier.padding(8.dp)
            )

        }

    }

}