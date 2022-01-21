package com.app.interntest.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.interntest.data.source.local.entity.CommentEntity
import com.app.interntest.data.source.local.entity.PostEntity
import com.app.interntest.data.source.local.entity.UserEntity


@Database(
    entities = [PostEntity::class, UserEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}