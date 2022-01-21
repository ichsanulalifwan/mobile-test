package com.app.interntest.domain.model

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val body: String
)
