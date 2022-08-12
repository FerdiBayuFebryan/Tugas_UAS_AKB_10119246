package com.example.consumerapp.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroConfig {

    companion object Factory {
        fun getRetroClientInstance(): ApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}