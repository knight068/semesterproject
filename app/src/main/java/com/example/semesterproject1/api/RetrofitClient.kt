package com.example.semesterproject1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val Base_Url ="https://graduate-final.onrender.com/"

   val instance : Api by lazy{
        val retrofit = Retrofit.Builder()
        .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
    .build()

    retrofit.create(Api::class.java)
    }
}