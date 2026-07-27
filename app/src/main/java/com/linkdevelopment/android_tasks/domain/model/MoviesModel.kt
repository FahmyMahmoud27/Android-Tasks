package com.linkdevelopment.android_tasks.domain.model

data class MoviesModel(
    val totalPages: Int,
    val movies: List<MoviesEntity>
)