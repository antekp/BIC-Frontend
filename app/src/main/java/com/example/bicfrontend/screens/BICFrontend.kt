package com.example.bicfrontend.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bicfrontend.viewmodels.BanksViewModel

@Composable
fun BICFrontend(modifier: Modifier = Modifier) {
    val banksViewModel: BanksViewModel = viewModel()

    HomeScreen(viewModel = banksViewModel)
}