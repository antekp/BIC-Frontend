package com.example.bicfrontend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicfrontend.network.BanksApi
import kotlinx.coroutines.launch

class BanksViewModel : ViewModel() {
    var BICUiState: String by mutableStateOf("")
        private set


    init {
        getBanks()
    }


    private fun getBanks() {
        viewModelScope.launch {
            val listResult = BanksApi.retrofitService.getBanks()
            BICUiState = listResult
        }
    }
}