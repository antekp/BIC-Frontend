package com.example.bicfrontend.network
import com.google.gson.annotations.SerializedName



data class BankResponse(
    @SerializedName("address") val address: String,
    @SerializedName("bankName") val bankName: String,
    @SerializedName("countryISO2") val countryISO2: String,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("isHeadquarter") val isHeadquarter: Boolean,
    @SerializedName("swiftCode") val swiftCode: String,
    @SerializedName("branches") val branches: List<BankResponse>? = null
)
data class CountryResponse(
    @SerializedName("countryISO2") val countryISO2: String,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("swiftCodes") val swiftCodes: List<SwiftCodeResponse>
)
data class SwiftCodeResponse(
    @SerializedName("address") val address: String,
    @SerializedName("bankName") val bankName: String,
    @SerializedName("countryISO2") val countryISO2: String,
    @SerializedName("isHeadquarter") val isHeadquarter: Boolean,
    @SerializedName("swiftCode") val swiftCode: String
)
data class BankRequest(
    @SerializedName("country_iso2_code") val countryISO2: String,
    @SerializedName("swift_code") val swiftCode: String,
    @SerializedName("bank_name") val bankName: String,
    @SerializedName("address") val address: String,
    @SerializedName("town_name") val townName: String,
    @SerializedName("country_name") val countryName: String,
    @SerializedName("is_headquater") val isHeadquarter: Boolean
)


