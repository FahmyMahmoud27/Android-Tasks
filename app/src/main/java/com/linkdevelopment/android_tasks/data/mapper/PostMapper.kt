package com.linkdevelopment.android_tasks.data.mapper

import com.linkdevelopment.android_tasks.data.model.PostDto
import com.linkdevelopment.android_tasks.domain.model.Post

fun PostDto.toPost(): Post {
    return Post(
        id = id ?: 0,
        title = title ?: "",
        content = content ?: "",
        image = image ?: ""
    )
}