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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bicfrontend.R
import com.example.bicfrontend.ui.theme.BackgroundColor
import com.example.bicfrontend.ui.theme.BottomBoxColor
import com.example.bicfrontend.ui.theme.BoxColor
import com.example.bicfrontend.ui.theme.OrangeFill
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
            .background(color = BackgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
        ){
            TextField(
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(focusedContainerColor = BoxColor,unfocusedContainerColor = BoxColor, focusedIndicatorColor = BackgroundColor, unfocusedIndicatorColor = BackgroundColor),
                value = swiftCode,
                onValueChange = { newValue -> swiftCode = newValue.uppercase() },
                modifier = Modifier
                        .width(250.dp),
                label = { Text("Enter SWIFT Code", color = Color.White) },

            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier
                    .fillMaxSize()
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

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = BottomBoxColor)
                            .fillMaxSize()
                            .border(color = OrangeFill, width = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Bank Name: ${bank.bankName}", fontWeight = FontWeight.Bold, color = Color.White)
                            Text(text = "Address: ${bank.address.ifBlank { "N/A" }}", color = Color.White)
                            Text(text = "Country: ${bank.countryName} (${bank.countryISO2})", color = Color.White)
                            Text(text = "Swift Code: ${bank.swiftCode}", color = Color.White)
                            Text(text = "Headquarter: ${if (bank.isHeadquarter) "Yes" else "No"}", color = Color.White)
                        }
                    }

                    if (bank.isHeadquarter && !bank.branches.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .background(color = BottomBoxColor)
                                .fillMaxSize()
                                .border(color = OrangeFill, width = 2.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "Branches:", fontWeight = FontWeight.Bold, color = Color.White
                            )
                        }

                        bank.branches.forEach { branch ->
                            Box(modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .background(color = BottomBoxColor)
                                .border(color = OrangeFill, width = 2.dp)) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = "• Branch Name: ${branch.bankName}",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(text = "  Address: ${branch.address.ifBlank { "N/A" }}",color = Color.White)
                                    Text(text = "  SWIFT Code: ${branch.swiftCode}",color = Color.White)
                                }
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

@Preview
@Composable
private fun SwiftPreview() {
    val viewModel = BanksViewModel()
    SearchBySwiftScreen(viewModel)
}