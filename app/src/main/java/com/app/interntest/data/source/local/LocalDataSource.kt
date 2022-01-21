package com.app.interntest.data.source.local

import com.app.interntest.data.source.local.entity.CommentEntity
import com.app.interntest.data.source.local.entity.PostEntity
import com.app.interntest.data.source.local.entity.UserEntity
import com.app.interntest.data.source.local.room.PostDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val postDao: PostDao) {

    fun getAllPost(): Flow<List<PostEntity>> = postDao.getAllPost()

    fun getUser(userId: Int): Flow<UserEntity> = postDao.getUser(userId)

    fun getAllComments(postId: Int): Flow<List<CommentEntity>> = postDao.getAllComments(postId)

    suspend fun insertPost(post: List<PostEntity>) = postDao.insertPost(post)

    suspend fun insertUser(user: List<UserEntity>) = postDao.insertUser(user)

    suspend fun insertComment(comment: List<CommentEntity>) = postDao.insertComment(comment)
}