package com.linkdevelopment.android_tasks.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieLocalEntity(

    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String
)
