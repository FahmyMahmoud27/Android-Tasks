package com.linkdevelopment.android_tasks.domain.usecase

import com.linkdevelopment.android_tasks.domain.model.Post
import com.linkdevelopment.android_tasks.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    suspend operator fun invoke(): List<Post> {
        return postsRepository.getPosts()
    }
}