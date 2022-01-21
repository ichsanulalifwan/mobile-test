package com.app.interntest.ui.detailpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.interntest.core.domain.usecase.PostUseCase

class DetailPostViewModel(postUseCase: PostUseCase) : ViewModel() {

    val postId = MutableLiveData<Int>()
    val userId = MutableLiveData<Int>()

    val comments = Transformations.switchMap(postId) { id ->
        postUseCase.getAllComments(id).asLiveData()
    }

    val user = Transformations.switchMap(userId) { id ->
        postUseCase.getUser(id).asLiveData()
    }

    fun setSelectedPost(postId: Int) {
        this.postId.value = postId
    }

    fun setSelectedUser(userId: Int) {
        this.userId.value = userId
    }
}