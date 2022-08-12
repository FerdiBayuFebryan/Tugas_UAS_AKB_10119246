package com.ferdibayu.githubuser2.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("login")
    @Expose
    val login: String? = null,

    @SerializedName("avatar_url")
    @Expose
    val avatar: String? = null,

    @SerializedName("html_url")
    @Expose
    val htmlUrl: String? = null
) : Parcelable