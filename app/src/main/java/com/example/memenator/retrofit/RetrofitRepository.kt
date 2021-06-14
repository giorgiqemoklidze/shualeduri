package com.example.memenator.retrofit


import com.example.memenator.models.ResponseModel
import retrofit2.Response

import retrofit2.http.GET

interface RetrofitRepository {

    @GET("/gimme/50")
    suspend fun getList (): Response<ResponseModel>
}