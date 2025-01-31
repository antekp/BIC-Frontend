package com.example.bicfrontend.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "http://10.0.2.2:8080"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BanksApiService {
    @GET("/banks/bySwift/{swiftCode}")
    suspend fun getBanks(@Path("swiftCode") swiftCode: String): BankResponse

    @GET("/banks/swift-codes/country/{countryISO2}")
    suspend fun getBanksByCountry(@Path("countryISO2") countryISO2: String): CountryResponse
}


object BanksApi {
    val retrofitService: BanksApiService by lazy {
        retrofit.create(BanksApiService::class.java)
    }
}