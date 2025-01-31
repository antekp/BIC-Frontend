package com.example.bicfrontend.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bicfrontend.screens.DeleteBankScreen
import com.example.bicfrontend.screens.InsertBankScreen
import com.example.bicfrontend.screens.SearchByCountryScreen
import com.example.bicfrontend.screens.SearchBySwiftScreen
import com.example.bicfrontend.screens.WelcomeScreen
import com.example.bicfrontend.viewmodels.BanksViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("search_by_swift") {
            val viewModel = remember { BanksViewModel() }
            SearchBySwiftScreen(viewModel = viewModel, navController = navController)
        }
        composable("search_by_country") {
            val viewModel = remember { BanksViewModel() }
            SearchByCountryScreen(viewModel = viewModel, navController = navController)
        }
        composable("delete_bank") { DeleteBankScreen(navController) }
        composable("insert_bank") {
            val viewModel = remember { BanksViewModel() }
            InsertBankScreen(viewModel,navController) }
    }
}