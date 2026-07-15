package com.linkdevelopment.android_tasks.data.repository

import com.linkdevelopment.android_tasks.data.api.ApiService
import com.linkdevelopment.android_tasks.data.mapper.toPost
import com.linkdevelopment.android_tasks.domain.model.Post
import com.linkdevelopment.android_tasks.domain.repository.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl@Inject constructor(
    private val apiService: ApiService
) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        val postsDto = apiService.getPosts()
        return postsDto.map {
            it.toPost()
        }
    }

}