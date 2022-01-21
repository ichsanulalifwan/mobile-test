package com.app.interntest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val companyName: String,
    val companyBs: String,
    val companyCatchPhrase: String,
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String,
    val geoLat: String,
    val geoLng: String
) : Parcelable
