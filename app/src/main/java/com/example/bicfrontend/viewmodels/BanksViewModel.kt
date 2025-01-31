package com.example.bicfrontend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicfrontend.network.BankResponse
import com.example.bicfrontend.network.BanksApi
import kotlinx.coroutines.launch
import java.io.IOException

class BanksViewModel : ViewModel() {
    var BICUiState: BankUiState by mutableStateOf(BankUiState.Loading)
        private set
    init {
        getBanks()
    }
    private fun getBanks() {
        viewModelScope.launch {
            try {
                val bankData = BanksApi.retrofitService.getBanks()
                BICUiState = BankUiState.Success(bankData)
            } catch (e: IOException) {
                BICUiState = BankUiState.Error
            }
        }
    }
}
sealed interface BankUiState {
    data class Success(val banks: BankResponse) : BankUiState
    object Error : BankUiState
    object Loading : BankUiState
}