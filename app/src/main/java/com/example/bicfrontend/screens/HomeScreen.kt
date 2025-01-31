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

import com.example.bicfrontend.viewmodels.BankUiState
import com.example.bicfrontend.viewmodels.BanksViewModel
import com.example.bicfrontend.viewmodels.CountryUiState

@Composable
fun HomeScreen(viewModel: BanksViewModel) {
    var swiftCode by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("") }

    val uiState by remember { derivedStateOf { viewModel.BICUiState } }
    val countryState by remember { derivedStateOf { viewModel.countryUiState } }

    val context = LocalContext.current
    val view = LocalView.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // 🔹 Wymuszenie przewijalności
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 TextField do wyszukiwania po kodzie kraju
        TextField(
            value = countryCode,
            onValueChange = { newValue ->
                countryCode = newValue.uppercase()
            },
            label = { Text("Enter Country Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Przycisk do wyszukiwania po kraju
        Button(
            onClick = {
                if (countryCode.isNotBlank()) {
                    viewModel.getBanksByCountry(countryCode)
                    hideKeyboard(context, view)
                }
            }
        ) {
            Text("Search by Country")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Wyniki dla wyszukiwania po kraju
        when (countryState) {
            is CountryUiState.Success -> {
                val country = (countryState as CountryUiState.Success).country

                Text(text = "Country Name: ${country.countryName}", fontWeight = FontWeight.Bold)
                Text(text = "Country ISO Code: ${country.countryISO2}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Banks in ${country.countryName}:", fontWeight = FontWeight.Bold)

                country.swiftCodes.forEach { bank ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "• Bank Name: ${bank.bankName}", fontWeight = FontWeight.Bold)
                        Text(text = "  Address: ${bank.address}")
                        Text(text = "  SWIFT Code: ${bank.swiftCode}")
                        Text(text = "  Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            is CountryUiState.Error -> {
                Text(text = (countryState as CountryUiState.Error).message, color = Color.Red)
            }
            else -> {}
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 TextField do wyszukiwania po SWIFT
        TextField(
            value = swiftCode,
            onValueChange = { newValue ->
                swiftCode = newValue.uppercase()
            },
            label = { Text("Enter SWIFT Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Przycisk do wyszukiwania po SWIFT
        Button(
            onClick = {
                if (swiftCode.isNotBlank()) {
                    viewModel.updateSwiftCode(swiftCode)
                    viewModel.getBanks()
                    hideKeyboard(context, view)
                }
            }
        ) {
            Text("Search by SWIFT")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Wyniki dla wyszukiwania po SWIFT
        when (uiState) {
            is BankUiState.Success -> {
                val bank = (uiState as BankUiState.Success).banks
                Column {
                    Text(text = "Bank Name: ${bank.bankName}", fontWeight = FontWeight.Bold)
                    Text(text = "Address: ${bank.address}")
                    Text(text = "Country: ${bank.countryName} (${bank.countryISO2})")
                    Text(text = "Swift Code: ${bank.swiftCode}")
                    Text(text = "Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}")
                }
            }
            is BankUiState.Error -> {
                Text(text = (uiState as BankUiState.Error).message, color = Color.Red)
            }
            else -> {}
        }
    }
}
