package com.app.interntest.data.source.remote

import android.util.Log
import com.app.interntest.data.source.remote.network.ApiResponse
import com.app.interntest.data.source.remote.network.ApiService
import com.app.interntest.data.source.remote.response.comment.CommentItem
import com.app.interntest.data.source.remote.response.post.PostItem
import com.app.interntest.data.source.remote.response.user.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllPost(): Flow<ApiResponse<List<PostItem>>> {

        //get all data posts from remote api
        return flow {
            try {
                val response = apiService.getAllPost()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(
                    TAG,
                    e.toString()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllUser(): Flow<ApiResponse<List<UserItem>>> {

        // get all data users from remote api
        return flow {
            try {
                val response = apiService.getAllUser()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(
                    TAG,
                    e.toString()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllComment(): Flow<ApiResponse<List<CommentItem>>> {

        // get all data comments from remote api
        return flow {
            try {
                val response = apiService.getAllComment()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(
                    TAG,
                    e.toString()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "RemoteDataSource"
    }
}