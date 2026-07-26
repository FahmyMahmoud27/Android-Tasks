package com.linkdevelopment.android_tasks.presentation.ui.utils

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Posts : Routes

    @Serializable
    data class Details(
        val id: Int,
        val title: String,
        val content: String,
        val image: String
    ) : Routes
}