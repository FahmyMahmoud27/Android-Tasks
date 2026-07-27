package com.linkdevelopment.android_tasks.data.remote.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val movies: List<Movie?>?
)