package com.example.bicfrontend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BanksViewModel : ViewModel() {
    var BICUiState: String by mutableStateOf("")
        private set


    init {
        getbanks()
    }


    fun getbanks() {
        BICUiState = "Set the Mars API status response here!"
    }
}