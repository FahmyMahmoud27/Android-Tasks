package com.linkdevelopment.android_tasks.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.linkdevelopment.android_tasks.data.local.dao.MoviesDao
import com.linkdevelopment.android_tasks.data.local.entity.MovieLocalEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val DATA_STORE_NAME = "_dataStoreName"
private const val GRID_SELECTED = "_gridSelected"
val Context.prefManager by preferencesDataStore(DATA_STORE_NAME)


class LocalDataSource @Inject constructor(
    @ApplicationContext private val mContext: Context,
    private val moviesDao: MoviesDao
) {
    companion object {
        private val IS_GRID_SELECTED =
            booleanPreferencesKey(GRID_SELECTED)
    }


    suspend fun saveIsGridSelected(isSelected: Boolean) {
        mContext.prefManager.edit { pref ->
            pref[IS_GRID_SELECTED] = isSelected
        }
    }


    fun getGridSelected(): Flow<Boolean> {
        return mContext.prefManager.data.map { pref ->
            pref[IS_GRID_SELECTED] ?: false
        }
    }


    suspend fun saveMovies(
        movies: List<MovieLocalEntity>
    ) {
        moviesDao.deleteMovies()
        moviesDao.insertMovies(movies)
    }


    suspend fun getMovies(): List<MovieLocalEntity> {
        return moviesDao.getMovies()
    }

}