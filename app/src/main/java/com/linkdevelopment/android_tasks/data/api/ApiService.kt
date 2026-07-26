package com.linkdevelopment.android_tasks.data.api

import com.linkdevelopment.android_tasks.data.model.PostDto
import retrofit2.http.GET

interface ApiService {


    @GET("posts")
    suspend fun getPosts(): List<PostDto>


}