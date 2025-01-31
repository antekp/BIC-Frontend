package com.example.bicfrontend.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicfrontend.network.BankResponse
import com.example.bicfrontend.network.BanksApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BanksViewModel : ViewModel() {
    var BICUiState by mutableStateOf<BankUiState>(BankUiState.Loading)
        private set

    var swiftCode: String by mutableStateOf("")

    fun updateSwiftCode(newCode: String) {
        swiftCode = newCode
        Log.d("VIEWMODEL_UPDATE", "swiftCode updated: $swiftCode")
    }

    fun getBanks() {
        if (swiftCode.isBlank()) return

        viewModelScope.launch {
            try {
                Log.d("API_CALL", "Sending request for SWIFT: $swiftCode")
                val bankData = BanksApi.retrofitService.getBanks(swiftCode)

                if (bankData.bankName.isBlank()) {  // Jeśli odpowiedź jest pusta, uznajmy, że bank nie istnieje
                    Log.e("API_ERROR", "Bank not found for SWIFT: $swiftCode")
                    BICUiState = BankUiState.Error("Bank doesn't exists")
                } else {
                    BICUiState = BankUiState.Success(bankData)
                }

            } catch (e: HttpException) {
                if (e.code() == 404) {
                    Log.e("API_ERROR", "404 Not Found for SWIFT: $swiftCode")
                    BICUiState = BankUiState.Error("Bank doesn't exists")
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
    data class Error(val message: String) : BankUiState  // Przechowuje wiadomość błędu
    object Loading : BankUiState
}