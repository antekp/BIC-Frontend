package com.example.bicfrontend


import com.example.bicfrontend.network.BankRequest
import com.example.bicfrontend.network.BanksApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BanksApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: BanksApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanksApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getBanks should return BankResponse when API responds successfully`() = runBlocking {
        val mockResponse = """
            {
                "address": "Test Address",
                "bankName": "Test Bank",
                "countryISO2": "US",
                "countryName": "United States",
                "isHeadquarter": true,
                "swiftCode": "TESTUS33"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        val result = apiService.getBanks("TESTUS33")

        assertThat(result.bankName).isEqualTo("Test Bank")
        assertThat(result.swiftCode).isEqualTo("TESTUS33")
    }

    @Test
    fun `getBanksByCountry should return CountryResponse when API responds successfully`() = runBlocking {
        val mockResponse = """
            {
                "countryISO2": "US",
                "countryName": "United States",
                "swiftCodes": [
                    {
                        "address": "Test Address",
                        "bankName": "Test Bank",
                        "countryISO2": "US",
                        "isHeadquarter": true,
                        "swiftCode": "TESTUS33"
                    }
                ]
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        val result = apiService.getBanksByCountry("US")

        assertThat(result.countryName).isEqualTo("United States")
        assertThat(result.swiftCodes).hasSize(1)
        assertThat(result.swiftCodes[0].swiftCode).isEqualTo("TESTUS33")
    }

    @Test
    fun `deleteBank should return success when API returns 204`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(204))

        val response = apiService.deleteBank("TESTUS33")

        assertThat(response.code()).isEqualTo(204)
    }

    @Test
    fun `insertBank should return success when API returns 201`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(201))

        val bankRequest = BankRequest(
            countryISO2 = "US",
            swiftCode = "TESTUS33",
            bankName = "Test Bank",
            address = "Test Address",
            townName = "New York",
            countryName = "United States",
            isHeadquarter = true
        )

        val response = apiService.insertBank(bankRequest)

        assertThat(response.code()).isEqualTo(201)
    }
}
