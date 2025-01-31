package com.example.bicfrontend.network
import retrofit2.converter.scalars.ScalarsConverterFactory

import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "http://10.0.2.2:8080"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BanksApiService{
    @GET("/banks")
    suspend fun getBanks(): String
}

object BanksApi {
    val retrofitService : BanksApiService by lazy {
        retrofit.create(BanksApiService::class.java)
    }
}