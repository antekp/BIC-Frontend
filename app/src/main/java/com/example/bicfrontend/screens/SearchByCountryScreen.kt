package com.example.bicfrontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bicfrontend.ui.theme.hideKeyboard
import com.example.bicfrontend.viewmodels.BanksViewModel
import com.example.bicfrontend.viewmodels.CountryUiState

@Composable
fun SearchByCountryScreen(viewModel: BanksViewModel, navController: NavController) {
    var countryCode by remember { mutableStateOf("") }
    val countryState by remember { derivedStateOf { viewModel.countryUiState } }
    val context = LocalContext.current
    val view = LocalView.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = countryCode,
            onValueChange = { newValue -> countryCode = newValue.uppercase() },
            label = { Text("Enter Country Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        when (countryState) {
            is CountryUiState.Success -> {
                val country = (countryState as CountryUiState.Success).country
                Column {
                    Text(text = "Country Name: ${country.countryName}", fontWeight = FontWeight.Bold)
                    Text(text = "Country ISO Code: ${country.countryISO2}")
                    country.swiftCodes.forEach { bank ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "• Bank Name: ${bank.bankName}", fontWeight = FontWeight.Bold)
                            Text(text = "  Address: ${bank.address}")
                            Text(text = "  SWIFT Code: ${bank.swiftCode}")
                            Text(text = "  Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}")
                        }
                    }
                }
            }
            is CountryUiState.Error -> {
                Text(text = (countryState as CountryUiState.Error).message, color = Color.Red)
            }
            else -> {}
        }
    }
}
