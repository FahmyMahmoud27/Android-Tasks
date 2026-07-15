package com.linkdevelopment.android_tasks.di

import com.linkdevelopment.android_tasks.data.repository.PostsRepositoryImpl
import com.linkdevelopment.android_tasks.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository

}