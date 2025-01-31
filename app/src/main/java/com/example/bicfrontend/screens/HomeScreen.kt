package com.example.bicfrontend.screens

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bicfrontend.R
import com.example.bicfrontend.network.BankResponse

import com.example.bicfrontend.viewmodels.BankUiState
import com.example.bicfrontend.viewmodels.BanksViewModel

@Composable
fun HomeScreen(viewModel: BanksViewModel) {
    var swiftCode by remember { mutableStateOf("") }
    val uiState by remember { derivedStateOf { viewModel.BICUiState } }
    val context = LocalContext.current
    val view = LocalView.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField + Button w jednym rzędzie
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = swiftCode,
                onValueChange = { newValue ->
                    swiftCode = newValue.uppercase() // Zamiana liter na wielkie
                },
                label = { Text("Enter SWIFT Code") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (swiftCode.isNotBlank()) {
                        Log.d("BUTTON_CLICK", "Button clicked with SWIFT: $swiftCode")
                        viewModel.updateSwiftCode(swiftCode)
                        viewModel.getBanks()
                        hideKeyboard(context, view)
                    }
                }
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Wyświetlanie odpowiedzi
        when (uiState) {
            is BankUiState.Success -> {
                val bank = (uiState as BankUiState.Success).banks
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Bank Name: ${bank.bankName}")
                    Text(text = "Address: ${bank.address.ifBlank { "N/A" }}")
                    Text(text = "Country: ${bank.countryName} (${bank.countryISO2})")
                    Text(text = "Swift Code: ${bank.swiftCode}")
                    Text(text = "Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}")
                }
            }
            is BankUiState.Loading -> { /* Puste ładowanie */ }
            is BankUiState.Error -> {
                val errorMessage = (uiState as BankUiState.Error).message
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


