package com.app.interntest.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val companyName: String? = null,
    val companyBs: String? = null,
    val companyCatchPhrase: String? = null,
    val city: String? = null,
    val street: String? = null,
    val suite: String? = null,
    val zipcode: String? = null,
    val geoLat: String? = null,
    val geoLng: String? = null
) : Parcelable
