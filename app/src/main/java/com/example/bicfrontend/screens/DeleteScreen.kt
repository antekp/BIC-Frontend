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
import androidx.compose.ui.text.input.ImeAction
import com.example.bicfrontend.viewmodels.BanksViewModel

import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteBankScreen(navController: NavController, viewModel: BanksViewModel = remember { BanksViewModel() }) {
    var swiftCode by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
            value = swiftCode,
            onValueChange = { swiftCode = it.uppercase() },
            label = { Text("Enter SWIFT Code to Delete") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
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
            Text("Delete Bank")
        }

        Spacer(modifier = Modifier.height(16.dp))

        SnackbarHost(hostState = snackbarHostState)
    }
}
