package com.example.bicfrontend.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicfrontend.network.BankRequest
import com.example.bicfrontend.network.BankResponse
import com.example.bicfrontend.network.BanksApi
import com.example.bicfrontend.network.CountryResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



class BanksViewModel : ViewModel() {
    var BICUiState by mutableStateOf<BankUiState>(BankUiState.Loading)
        private set

    var swiftCode: String by mutableStateOf("")

    var countryUiState by mutableStateOf<CountryUiState>(CountryUiState.Loading)
        private set

    var insertState by mutableStateOf<ResultState>(ResultState.Idle)
        private set

    fun updateSwiftCode(newCode: String) {
        BICUiState = BankUiState.Loading
        swiftCode = newCode
    }
    fun getBanksByCountry(countryISO2: String) {
        if (countryISO2.isBlank()) return

        viewModelScope.launch {
            try {
                Log.d("API_CALL", "Fetching banks for country: $countryISO2")
                val response = BanksApi.retrofitService.getBanksByCountry(countryISO2)

                if (response.swiftCodes.isNotEmpty()) {
                    countryUiState = CountryUiState.Success(response)
                } else {
                    countryUiState = CountryUiState.Error("No banks found")
                }
            } catch (e: HttpException) {
                countryUiState = CountryUiState.Error("Server error")
            } catch (e: IOException) {
                countryUiState = CountryUiState.Error("No server connection")
            }
        }
    }
    fun insertBank(bank: BankRequest) {
        viewModelScope.launch {
            insertState = ResultState.Loading
            try {
                val uppercaseBank = bank.copy(
                    countryISO2 = bank.countryISO2.uppercase(),
                    swiftCode = bank.swiftCode.uppercase(),
                    bankName = bank.bankName.uppercase(),
                    address = bank.address.uppercase()
                )
                val response = BanksApi.retrofitService.insertBank(uppercaseBank)
                if (response.isSuccessful) {
                    insertState = ResultState.Success("Bank added successfully!")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    insertState = ResultState.Error("Failed to add bank: $errorMessage")
                }
            } catch (e: Exception) {
                insertState = ResultState.Error("Network error: ${e.message}")
            }
        }
    }
    suspend fun deleteBank(swiftCode: String): Boolean {
        return try {
            val response = BanksApi.retrofitService.deleteBank(swiftCode)
            response.isSuccessful && response.code() == 204
        } catch (e: Exception) {
            false
        }
    }

    fun getBanks() {
        if (swiftCode.isBlank()) return

        viewModelScope.launch {
            try {
                Log.d("API_CALL", "Sending request for SWIFT: $swiftCode")
                val response = BanksApi.retrofitService.getBanks(swiftCode)

                if (response.isHeadquarter) {
                    Log.d("API_CALL", "Received HeadquarterResponse: ${response.bankName}")
                    BICUiState = BankUiState.Success(response)
                } else {
                    Log.d("API_CALL", "Received BranchResponse: ${response.bankName}")
                    BICUiState = BankUiState.Success(response)
                }
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    Log.e("API_ERROR", "404 Not Found for SWIFT: $swiftCode")
                    BICUiState = BankUiState.Error("Such bank doesn't exists")
                } else {
                    Log.e("API_ERROR", "Network error", e)
                    BICUiState = BankUiState.Error("Network error")
                }
            } catch (e: IOException) {
                Log.e("API_ERROR", "Network error occurred", e)
                BICUiState = BankUiState.Error("Failed to connect with server")
            }
        }
    }
}
sealed interface BankUiState {
    data class Success(val banks: BankResponse) : BankUiState
    data class Error(val message: String) : BankUiState
    object Loading : BankUiState
}
sealed interface CountryUiState {
    data class Success(val country: CountryResponse) : CountryUiState
    data class Error(val message: String) : CountryUiState
    object Loading : CountryUiState
}
sealed interface ResultState {
    data class Success(val message: String) : ResultState
    data class Error(val message: String) : ResultState
    object Loading : ResultState
    object Idle : ResultState
}