package com.app.interntest.core.data.source.local.room

import androidx.room.*
import com.app.interntest.core.data.source.local.entity.CommentEntity
import com.app.interntest.core.data.source.local.entity.PostEntity
import com.app.interntest.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    // Get Data
    @Query("SELECT * FROM post")
    fun getAllPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId: Int): Flow<UserEntity>

    @Query("SELECT * FROM comment WHERE post_id = :postId")
    fun getAllComments(postId: Int): Flow<List<CommentEntity>>

    // Insert Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: List<CommentEntity>)
}