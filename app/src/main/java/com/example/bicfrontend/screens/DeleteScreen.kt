package com.example.bicfrontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.bicfrontend.R
import com.example.bicfrontend.viewmodels.BanksViewModel

import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteBankScreen(viewModel: BanksViewModel = remember { BanksViewModel() }) {
    var swiftCode by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = swiftCode,
                onValueChange = { swiftCode = it.uppercase() },
                label = { Text("Enter SWIFT Code to Delete") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.height(55.dp),
                onClick = {
                    if (swiftCode.isNotBlank()) {
                        coroutineScope.launch {
                            val result = viewModel.deleteBank(swiftCode)
                            val message = if (result) {
                                "Bank with SWIFT: $swiftCode deleted successfully"
                            } else {
                                "No bank found with SWIFT: $swiftCode"
                            }
                            snackbarHostState.showSnackbar(message)
                            swiftCode = ""
                        }
                        keyboardController?.hide()
                    }
                }
            ) {
                Text("Delete")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        SnackbarHost(hostState = snackbarHostState)
    }
}
