package com.linkdevelopment.android_tasks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val image: String
)
