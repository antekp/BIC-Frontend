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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bicfrontend.R
import com.example.bicfrontend.ui.theme.hideKeyboard
import com.example.bicfrontend.viewmodels.BankUiState
import com.example.bicfrontend.viewmodels.BanksViewModel


@Composable
fun SearchBySwiftScreen(viewModel: BanksViewModel) {
    var swiftCode by remember { mutableStateOf("") }
    val uiState by remember { derivedStateOf { viewModel.BICUiState } }
    val context = LocalContext.current
    val view = LocalView.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = swiftCode,
                onValueChange = { newValue -> swiftCode = newValue.uppercase() },
                label = { Text("Enter SWIFT Code") },
                modifier = Modifier.width(250.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.fillMaxSize().height(55.dp),
                onClick = {
                    if (swiftCode.isNotBlank()) {
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

        when (uiState) {
            is BankUiState.Success -> {
                val bank = (uiState as BankUiState.Success).banks

                Column {
                    Text(text = "Bank Name: ${bank.bankName}", fontWeight = FontWeight.Bold)
                    Text(text = "Address: ${bank.address.ifBlank { "N/A" }}")
                    Text(text = "Country: ${bank.countryName} (${bank.countryISO2})")
                    Text(text = "Swift Code: ${bank.swiftCode}")
                    Text(text = "Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}")

                    if (bank.isHeadquarter && !bank.branches.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Branches:", fontWeight = FontWeight.Bold)

                        bank.branches.forEach { branch ->
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = "• Branch Name: ${branch.bankName}", fontWeight = FontWeight.Bold)
                                Text(text = "  Address: ${branch.address.ifBlank { "N/A" }}")
                                Text(text = "  SWIFT Code: ${branch.swiftCode}")
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
            is BankUiState.Error -> {
                Text(text = (uiState as BankUiState.Error).message, color = Color.Red)
            }
            else -> {}
        }
    }
}
