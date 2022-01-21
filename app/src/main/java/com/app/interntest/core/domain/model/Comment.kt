package com.app.interntest.core.domain.model

import kotlinx.android.parcel.Parcelize

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val body: String
)
