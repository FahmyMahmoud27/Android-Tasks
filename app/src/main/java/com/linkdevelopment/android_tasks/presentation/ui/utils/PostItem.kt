package com.linkdevelopment.android_tasks.presentation.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linkdevelopment.android_tasks.domain.model.Post

@Composable
fun PostItem(
    post: Post,
    onClick: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Text(
            text = post.title,
            modifier = Modifier
                .clickable { onClick() }
                .padding(15.dp),
            style = MaterialTheme.typography.headlineSmall
        )
    }


}