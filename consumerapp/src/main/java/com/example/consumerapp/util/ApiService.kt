package com.example.consumerapp.util

import com.example.consumerapp.model.ResponseUser
import com.example.consumerapp.model.User
import com.example.consumerapp.model.detailuser.DetailsUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization:token a661077c84ca4a463adf6d516d64947de2fda456")
    fun getSearchData(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization:token a661077c84ca4a463adf6d516d64947de2fda456")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DetailsUsers>

    @GET("users/{username}/followers")
    @Headers("Authorization:token a661077c84ca4a463adf6d516d64947de2fda456")
    fun getFollowersData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization:token a661077c84ca4a463adf6d516d64947de2fda456")
    fun getFollowingData(
        @Path("username") username: String,
        @Query("page") page: String
    ): Call<List<User>>


}