package com.linkdevelopment.android_tasks.domain.repository

import com.linkdevelopment.android_tasks.domain.model.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}