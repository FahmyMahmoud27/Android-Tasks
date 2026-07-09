package com.linkdevelopment.android_tasks.data

import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val createdAt: String
)