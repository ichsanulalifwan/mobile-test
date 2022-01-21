package com.app.interntest.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "post_id")
    val postId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "body")
    val body: String
)
