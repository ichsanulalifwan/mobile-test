package com.app.interntest.data.source.remote.response.user


import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("address")
    val address: Address,
    @SerializedName("company")
    val company: Company,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)