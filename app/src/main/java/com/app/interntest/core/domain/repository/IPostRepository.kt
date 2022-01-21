package com.app.interntest.core.domain.repository

import com.app.interntest.core.data.Resource
import com.app.interntest.core.domain.model.Comment
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IPostRepository {

    fun getAllPost(): Flow<Resource<List<Post>>>

    fun getUser(userId: Int): Flow<Resource<User>>

    fun getAllComments(postId: Int): Flow<Resource<List<Comment>>>
}