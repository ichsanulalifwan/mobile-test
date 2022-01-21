package com.app.interntest.data.source.remote.response.comment


import com.google.gson.annotations.SerializedName

data class CommentItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
)