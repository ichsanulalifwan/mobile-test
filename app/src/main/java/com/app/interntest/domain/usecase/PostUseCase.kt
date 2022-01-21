package com.app.interntest.domain.usecase

import com.app.interntest.data.Resource
import com.app.interntest.domain.model.Comment
import com.app.interntest.domain.model.Post
import com.app.interntest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PostUseCase {

    fun getAllPost(): Flow<Resource<List<Post>>>

    fun getUser(userId: Int): Flow<Resource<User>>

    fun getAllComments(postId: Int): Flow<Resource<List<Comment>>>
}