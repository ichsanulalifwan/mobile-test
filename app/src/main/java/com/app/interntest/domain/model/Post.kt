package com.app.interntest.domain.model

data class Post(
    val postId: Int,
    val userId: Int,
    val title: String,
    val body: String
)
