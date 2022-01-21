package com.app.interntest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val postId: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Parcelable
