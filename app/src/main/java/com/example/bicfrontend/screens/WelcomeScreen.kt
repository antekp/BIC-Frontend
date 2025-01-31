package com.example.bicfrontend.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Bank Search App", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("search_by_swift") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Search by SWIFT Code")
        }

        Button(
            onClick = { navController.navigate("search_by_country") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Search by Country")
        }

        Button(
            onClick = { navController.navigate("delete_bank") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Delete Bank")
        }

        Button(
            onClick = { navController.navigate("insert_bank") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "Insert Bank")
        }
    }
}