package com.app.interntest.ui.listpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.interntest.core.domain.usecase.PostUseCase

class ListPostViewModel(postUseCase: PostUseCase) : ViewModel() {

    val userId = MutableLiveData<Int>()

    val listPost = postUseCase.getAllPost().asLiveData()
    val user = Transformations.switchMap(userId) { id ->
        postUseCase.getUser(id).asLiveData()
    }

    fun setSelectedUser(userId: Int) {
        this.userId.value = userId
    }
}