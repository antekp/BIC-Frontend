package com.example.bicfrontend.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bank Search App", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp).size(64.dp)
        ) {
            Text(text = "Search by SWIFT Code", fontSize = 16.sp)
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp).size(64.dp)
        ) {
            Text(text = "Search by Country",fontSize = 16.sp)
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp).size(64.dp)
        ) {
            Text(text = "Delete Bank",fontSize = 16.sp)
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp).size(64.dp)
        ) {
            Text(text = "Insert Bank",fontSize = 16.sp)
        }
    }
}