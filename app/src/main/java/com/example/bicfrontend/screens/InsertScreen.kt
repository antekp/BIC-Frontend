package com.example.bicfrontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bicfrontend.network.BankRequest
import com.example.bicfrontend.viewmodels.BanksViewModel
import com.example.bicfrontend.viewmodels.ResultState

@Composable
fun InsertBankScreen(viewModel: BanksViewModel) {
    var countryISO2 by remember { mutableStateOf("") }
    var swiftCode by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var townName by remember { mutableStateOf("") }
    var countryName by remember { mutableStateOf("") }
    var isHeadquarter by remember { mutableStateOf(false) }

    val insertState = viewModel.insertState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Bank", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = countryISO2, onValueChange = { countryISO2 = it.uppercase() }, label = { Text("Country ISO2") })
        OutlinedTextField(value = swiftCode, onValueChange = { swiftCode = it.uppercase() }, label = { Text("Swift Code") })
        OutlinedTextField(value = bankName, onValueChange = { bankName = it.uppercase() }, label = { Text("Bank Name") })
        OutlinedTextField(value = address, onValueChange = { address = it.uppercase() }, label = { Text("Address") })
        OutlinedTextField(value = townName, onValueChange = { townName = it.uppercase() }, label = { Text("Town Name") })
        OutlinedTextField(value = countryName, onValueChange = { countryName = it.uppercase() }, label = { Text("Country Name") })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Is Headquarter?")
            Switch(checked = isHeadquarter, onCheckedChange = { isHeadquarter = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.insertBank(
                BankRequest(
                    countryISO2 = countryISO2,
                    swiftCode = swiftCode,
                    bankName = bankName,
                    address = address,
                    townName = townName,
                    countryName = countryName,
                    isHeadquarter = isHeadquarter
                )
            )
        }) {
            Text("Insert Bank")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (insertState) {
            is ResultState.Loading -> CircularProgressIndicator()
            is ResultState.Success -> Text(text = insertState.message, color = Color.Green)
            is ResultState.Error -> Text(text = insertState.message, color = Color.Red)
            else -> {}
        }
    }
}
