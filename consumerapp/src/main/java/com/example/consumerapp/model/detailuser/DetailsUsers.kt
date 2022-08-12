package com.example.consumerapp.model.detailuser

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsUsers(
    @SerializedName("login")
    @Expose
    var login: String? = null,

    @SerializedName("avatar_url")
    @Expose
    var avatar: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("following")
    @Expose
    var following: Int? = null,

    @SerializedName("followers")
    @Expose
    var followers: Int? = null,

    @SerializedName("location")
    @Expose
    val location: String? = null,

    @SerializedName("public_repos")
    @Expose
    val publicRepos: Int? = null,

    @SerializedName("blog")
    @Expose
    val blog: String? = null,

    @SerializedName("bio")
    @Expose
    val bio: String


): Parcelable