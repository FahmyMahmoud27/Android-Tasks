package com.linkdevelopment.android_tasks.data.remote.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("results")
    val movies: List<Movie?>? = null
)