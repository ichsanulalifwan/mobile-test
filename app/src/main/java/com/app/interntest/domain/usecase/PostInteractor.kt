package com.app.interntest.domain.usecase

import com.app.interntest.data.PostRepository
import com.app.interntest.data.Resource
import com.app.interntest.domain.model.Comment
import com.app.interntest.domain.model.Post
import com.app.interntest.domain.model.User
import kotlinx.coroutines.flow.Flow

class PostInteractor(private val postRepository: PostRepository) : PostUseCase {

    override fun getAllPost(): Flow<Resource<List<Post>>> = postRepository.getAllPost()

    override fun getUser(userId: Int): Flow<Resource<User>> = postRepository.getUser(userId)

    override fun getAllComments(postId: Int): Flow<Resource<List<Comment>>> =
        postRepository.getAllComments(postId)
}