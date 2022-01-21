package com.app.interntest.core.data.source.remote.response.post


import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)