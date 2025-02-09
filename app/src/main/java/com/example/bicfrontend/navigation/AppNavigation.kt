package com.example.bicfrontend.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.bicfrontend.screens.DeleteBankScreen
import com.example.bicfrontend.screens.InsertBankScreen
import com.example.bicfrontend.screens.SearchByCountryScreen
import com.example.bicfrontend.screens.SearchBySwiftScreen
import com.example.bicfrontend.screens.WelcomeScreen
import com.example.bicfrontend.viewmodels.BanksViewModel

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndexItem: Int, viewModel: BanksViewModel) {
    when(selectedIndexItem){
        0 -> WelcomeScreen()
        1 -> SearchBySwiftScreen(viewModel)
        2 -> SearchByCountryScreen(viewModel)
        3 -> DeleteBankScreen()
        4 -> InsertBankScreen(viewModel)
    }

}