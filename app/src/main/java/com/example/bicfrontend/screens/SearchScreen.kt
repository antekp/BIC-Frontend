package com.example.bicfrontend.screens

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.example.bicfrontend.viewmodels.BanksViewModel

@Composable
fun SearchScreen(viewModel: BanksViewModel) {
    var swiftCode by remember { mutableStateOf("") }

    // Pobierz kontekst i widok w @Composable, żeby uniknąć błędu
    val context = LocalContext.current
    val view = LocalView.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = swiftCode,
            onValueChange = { swiftCode = it },
            label = { Text("Enter SWIFT Code") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (swiftCode.isNotBlank()) {
                Log.d("BUTTON_CLICK", "Button clicked with SWIFT: $swiftCode")
                viewModel.updateSwiftCode(swiftCode)
                viewModel.getBanks()

                // Ukrywanie klawiatury po kliknięciu
                hideKeyboard(context, view)
            }
        }) {
            Text("Search")
        }
    }
}
fun hideKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}