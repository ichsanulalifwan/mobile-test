package com.app.interntest.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "company_name")
    val companyName: String,

    @ColumnInfo(name = "company_bs")
    val companyBs: String,

    @ColumnInfo(name = "company_catch_phrase")
    val companyCatchPhrase: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "street")
    val street: String,

    @ColumnInfo(name = "suite")
    val suite: String,

    @ColumnInfo(name = "zipcode")
    val zipcode: String,

    @ColumnInfo(name = "geo_lat")
    val geoLat: String,

    @ColumnInfo(name = "geo_lng")
    val geoLng: String
)
