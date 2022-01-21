package com.app.interntest.core.domain.usecase

import com.app.interntest.core.data.Resource
import com.app.interntest.core.domain.model.Comment
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import com.app.interntest.core.domain.repository.IPostRepository
import kotlinx.coroutines.flow.Flow

class PostInteractor(private val postRepository: IPostRepository) : PostUseCase {

    override fun getAllPost(): Flow<Resource<List<Post>>> = postRepository.getAllPost()

    override fun getUser(userId: Int): Flow<Resource<User>> = postRepository.getUser(userId)

    override fun getAllComments(postId: Int): Flow<Resource<List<Comment>>> =
        postRepository.getAllComments(postId)
}