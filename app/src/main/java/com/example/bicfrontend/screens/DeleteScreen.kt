package com.example.bicfrontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.bicfrontend.R
import com.example.bicfrontend.ui.theme.BackgroundColor
import com.example.bicfrontend.ui.theme.BottomBoxColor
import com.example.bicfrontend.ui.theme.BoxColor
import com.example.bicfrontend.ui.theme.OrangeFill
import com.example.bicfrontend.viewmodels.BanksViewModel

import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteBankScreen(viewModel: BanksViewModel = remember { BanksViewModel() }) {
    var swiftCode by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
        ){
            TextField(
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor),
                value = swiftCode,
                onValueChange = { swiftCode = it.uppercase() },
                label = { Text("Enter SWIFT Code to Delete", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
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

@Preview
@Composable
private fun DeleteScreenPreview() {
    val viewmodel = BanksViewModel()
    DeleteBankScreen(viewmodel)
}