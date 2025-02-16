package com.example.bicfrontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bicfrontend.network.BankRequest
import com.example.bicfrontend.ui.theme.BackgroundColor
import com.example.bicfrontend.ui.theme.BottomBoxColor
import com.example.bicfrontend.ui.theme.BoxColor
import com.example.bicfrontend.ui.theme.OrangeFill
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
            .background(color = BackgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        TextField(value = countryISO2,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = { countryISO2 = it.uppercase() },
            label = { Text("Country ISO2", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        TextField(value = swiftCode,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = {
                swiftCode = it.uppercase()
                if(swiftCode.endsWith("XXX",ignoreCase = true)){
                    isHeadquarter = true
                } else{
                    isHeadquarter = false
                }},
            label = { Text("Swift Code", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        TextField(value = bankName,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = { bankName = it.uppercase() },
            label = { Text("Bank Name", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        TextField(value = address,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = { address = it.uppercase() },
            label = { Text("Address", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        TextField(value = townName,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = { townName = it.uppercase() },
            label = { Text("Town Name", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        TextField(value = countryName,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
            onValueChange = { countryName = it.uppercase() },
            label = { Text("Country Name", color = Color.White) },
            modifier = Modifier.padding(4.dp))

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            modifier = Modifier
                .height(55.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(width = 2.dp, color = OrangeFill, shape = RoundedCornerShape(16.dp))
                .background(color = BottomBoxColor),
            colors = ButtonColors(
                containerColor = BottomBoxColor,
                contentColor = OrangeFill,
                disabledContentColor = OrangeFill,
                disabledContainerColor = BottomBoxColor
            ),
            onClick = {
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

        when (insertState) {
            is ResultState.Loading -> CircularProgressIndicator()
            is ResultState.Success -> Text(text = insertState.message, color = Color.Green)
            is ResultState.Error -> Text(text = insertState.message, color = Color.Red)
            else -> {}
        }
    }
}

@Preview
@Composable
private fun InsertBankScreenPreview() {
    val viewModel = BanksViewModel()
    InsertBankScreen(viewModel)
}