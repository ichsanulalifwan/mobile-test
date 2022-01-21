package com.app.interntest.data.source.remote.network

import com.app.interntest.data.source.remote.response.comment.CommentResponse
import com.app.interntest.data.source.remote.response.post.PostResponse
import com.app.interntest.data.source.remote.response.user.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getAllPost(): PostResponse

    @GET("/users")
    suspend fun getAllUser(): UserResponse

    @GET("/comments")
    suspend fun getAllComment(): CommentResponse
}