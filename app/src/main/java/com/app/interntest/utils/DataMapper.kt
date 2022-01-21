package com.app.interntest.utils

import com.app.interntest.data.source.local.entity.CommentEntity
import com.app.interntest.data.source.local.entity.PostEntity
import com.app.interntest.data.source.local.entity.UserEntity
import com.app.interntest.data.source.remote.response.comment.CommentItem
import com.app.interntest.data.source.remote.response.post.PostItem
import com.app.interntest.data.source.remote.response.user.UserItem
import com.app.interntest.domain.model.Comment
import com.app.interntest.domain.model.Post
import com.app.interntest.domain.model.User

object DataMapper {

    fun mapEntitiesToPostDomain(input: List<PostEntity>): List<Post> =
        input.map {
            Post(
                postId = it.postId,
                userId = it.userId,
                title = it.title,
                body = it.body
            )
        }

    fun mapResponsesToPostEntities(input: List<PostItem>): List<PostEntity> {
        val postList = ArrayList<PostEntity>()
        input.map {
            val post =
                PostEntity(
                    postId = it.id,
                    userId = it.userId,
                    title = it.title,
                    body = it.body
                )
            postList.add(post)
        }
        return postList
    }

    fun mapEntitiesToUserDomain(input: UserEntity) = User(
        id = input.id,
        name = input.name,
        email = input.email,
        companyName = input.companyName,
        companyBs = input.companyBs,
        companyCatchPhrase = input.companyCatchPhrase,
        city = input.city,
        street = input.street,
        suite = input.suite,
        zipcode = input.zipcode,
        geoLat = input.geoLat,
        geoLng = input.geoLng
    )


    fun mapResponsesToUserEntities(input: List<UserItem>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user =
                UserEntity(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    companyName = it.company.name,
                    companyBs = it.company.bs,
                    companyCatchPhrase = it.company.catchPhrase,
                    city = it.address.city,
                    street = it.address.street,
                    suite = it.address.suite,
                    zipcode = it.address.zipcode,
                    geoLat = it.address.geo.lat,
                    geoLng = it.address.geo.lng
                )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToCommentDomain(input: List<CommentEntity>): List<Comment> =
        input.map {
            Comment(
                id = it.id,
                postId = it.postId,
                name = it.name,
                body = it.body
            )
        }

    fun mapResponsesToCommentEntities(input: List<CommentItem>): List<CommentEntity> {
        val commentList = ArrayList<CommentEntity>()
        input.map {
            val comment =
                CommentEntity(
                    id = it.id,
                    postId = it.postId,
                    name = it.name,
                    body = it.body
                )
            commentList.add(comment)
        }
        return commentList
    }
}