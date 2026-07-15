package com.linkdevelopment.android_tasks.data.model

import com.google.gson.annotations.SerializedName

data class PostDto(
	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("content")
	val content: String? = null,

	@SerializedName("image")
	val image: String? = null,

	@SerializedName("thumbnail")
	val thumbnail: String? = null,

	@SerializedName("publishedAt")
	val publishedAt: String? = null,

	@SerializedName("category")
	val category: String? = null,

	@SerializedName("userId")
	val userId: Int? = null,

	@SerializedName("slug")
	val slug: String? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("status")
	val status: String? = null,

	@SerializedName("updatedAt")
	val updatedAt: String? = null
)