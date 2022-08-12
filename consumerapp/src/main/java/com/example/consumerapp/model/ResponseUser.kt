package com.example.consumerapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int,

    @SerializedName("incomplete_results")
    @Expose
    val incompleteResult: Boolean,

    @SerializedName("items")
    @Expose
    val items: List<User>

) : Parcelable