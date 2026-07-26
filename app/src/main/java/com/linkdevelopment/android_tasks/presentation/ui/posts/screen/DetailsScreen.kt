package com.linkdevelopment.android_tasks.presentation.ui.posts.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linkdevelopment.android_tasks.domain.model.Post


@Composable
fun DetailsScreen(
    id: Int,
    title: String,
    content: String,
    image: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}