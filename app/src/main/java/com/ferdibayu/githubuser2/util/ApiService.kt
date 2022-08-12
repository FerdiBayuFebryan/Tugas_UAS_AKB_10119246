package com.ferdibayu.githubuser2.util

import com.ferdibayu.githubuser2.model.ResponseUser
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.model.detailuser.DetailsUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization:token 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")
    fun getSearchData(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization:token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DetailsUsers>

    @GET("users/{username}/followers")
    @Headers("Authorization:token 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")
    fun getFollowersData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization:token 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")
    fun getFollowingData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>


}