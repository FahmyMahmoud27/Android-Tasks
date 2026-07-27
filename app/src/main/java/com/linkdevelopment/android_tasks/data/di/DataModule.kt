package com.linkdevelopment.android_tasks.data.di

import android.content.Context
import androidx.room.Room
import com.linkdevelopment.android_tasks.data.local.LocalDataSource
import com.linkdevelopment.android_tasks.data.local.dao.MoviesDao
import com.linkdevelopment.android_tasks.data.local.database.MoviesDatabase
import com.linkdevelopment.android_tasks.data.remote.MoviesApi
import com.linkdevelopment.android_tasks.data.remote.remotedatasource.IRemoteDataSource
import com.linkdevelopment.android_tasks.data.remote.remotedatasource.RemoteDataSource
import com.linkdevelopment.android_tasks.data.remote.validate.IValidateRemoteDataSource
import com.linkdevelopment.android_tasks.data.remote.validate.ValidateRemoteDataSource
import com.linkdevelopment.android_tasks.data.repository.MoviesRepository
import com.linkdevelopment.android_tasks.domain.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
   fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
       return Retrofit.Builder()
           .baseUrl("https://api.themoviedb.org/3/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
   }


    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideValidateRemoteDataSource(): IValidateRemoteDataSource {
        return ValidateRemoteDataSource()
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        moviesApi: MoviesApi,
        validateRemoteDataSource: IValidateRemoteDataSource
    ): IRemoteDataSource {
        return RemoteDataSource(
            moviesApi,
            validateRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        iRemoteDataSource: IRemoteDataSource,
        localDataSource: LocalDataSource
    ): IMoviesRepository {
        return MoviesRepository(
            iRemoteDataSource,
            localDataSource
        )
    }



    @Provides
    @Singleton
    fun provideMoviesDatabase(
        @ApplicationContext context: Context
    ): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideMoviesDao(
        moviesDatabase: MoviesDatabase
    ): MoviesDao {
        return moviesDatabase.moviesDao()
    }






}