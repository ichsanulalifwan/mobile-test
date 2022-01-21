package com.app.interntest.core.data

import com.app.interntest.core.data.source.local.LocalDataSource
import com.app.interntest.core.data.source.remote.RemoteDataSource
import com.app.interntest.core.data.source.remote.network.ApiResponse
import com.app.interntest.core.data.source.remote.response.comment.CommentItem
import com.app.interntest.core.data.source.remote.response.post.PostItem
import com.app.interntest.core.data.source.remote.response.user.UserItem
import com.app.interntest.core.domain.model.Comment
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import com.app.interntest.core.domain.repository.IPostRepository
import com.app.interntest.core.utils.AppExecutors
import com.app.interntest.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPostRepository {

    override fun getAllPost(): Flow<Resource<List<Post>>> =
        object : NetworkBoundResource<List<Post>, List<PostItem>>() {
            override fun loadFromDB(): Flow<List<Post>> {
                return localDataSource.getAllPost().map {
                    DataMapper.mapEntitiesToPostDomain(it)
                }
            }

            override fun shouldFetch(data: List<Post>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PostItem>>> =
                remoteDataSource.getAllPost()

            override suspend fun saveCallResult(data: List<PostItem>) {
                val postList = DataMapper.mapResponsesToPostEntities(data)
                localDataSource.insertPost(postList)
            }
        }.asFlow()

    override fun getUser(userId: Int): Flow<Resource<User>> =
        object : NetworkBoundResource<User, List<UserItem>>() {
            override fun loadFromDB(): Flow<User> {
                return localDataSource.getUser(userId).map {
                    DataMapper.mapEntitiesToUserDomain(it)
                }
            }

            override fun shouldFetch(data: User?): Boolean =
                data == null || data.equals(emptyList<User>())

            override suspend fun createCall(): Flow<ApiResponse<List<UserItem>>> =
                remoteDataSource.getAllUser()

            override suspend fun saveCallResult(data: List<UserItem>) {
                val userList = DataMapper.mapResponsesToUserEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getAllComments(postId: Int): Flow<Resource<List<Comment>>> =
        object : NetworkBoundResource<List<Comment>, List<CommentItem>>() {
            override fun loadFromDB(): Flow<List<Comment>> {
                return localDataSource.getAllComments(postId).map {
                    DataMapper.mapEntitiesToCommentDomain(it)
                }
            }

            override fun shouldFetch(data: List<Comment>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CommentItem>>> =
                remoteDataSource.getAllComment()

            override suspend fun saveCallResult(data: List<CommentItem>) {
                val commentList = DataMapper.mapResponsesToCommentEntities(data)
                localDataSource.insertComment(commentList)
            }
        }.asFlow()
}