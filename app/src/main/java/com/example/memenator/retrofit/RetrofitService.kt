package com.example.memenator.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BaseUrl = "https://meme-api.herokuapp.com"

    fun RetrofitService() : RetrofitRepository{

        return Retrofit.Builder().baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitRepository::class.java)
    }
}