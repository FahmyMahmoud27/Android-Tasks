package com.linkdevelopment.android_tasks.data.remote.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null


)