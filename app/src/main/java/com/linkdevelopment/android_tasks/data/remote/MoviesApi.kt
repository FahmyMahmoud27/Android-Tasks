package com.linkdevelopment.android_tasks.data.remote

import com.linkdevelopment.android_tasks.data.remote.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MoviesApi {
    companion object {
        private const val NOW_PLAYING_MOVIES = "movie/now_playing"
    }

    @GET(NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(
        @Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyOTFhMGViY2VkZmZiM2I1NjUzMmJjMDE1YjY2NGI4NyIsIm5iZiI6MTY5Mjg4MTE4My40NzgwMDAyLCJzdWIiOiI2NGU3NTExZjFmZWFjMTAxMWIyYzkxM2YiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0._acwCWS6sLN-46h_8Z3FLJL5Jb0UQpAG943W5A4g2RY"
    ): Response<MoviesResponse>
}