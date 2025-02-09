package com.example.bicfrontend.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bicfrontend.ui.theme.Add
import com.example.bicfrontend.ui.theme.Home
import com.example.bicfrontend.ui.theme.Search
import com.example.bicfrontend.ui.theme.Trash

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)
val listOfNavigationItems = listOf(
    BottomNavigationItem("Home", "welcome", Home),
    BottomNavigationItem("Swift", "search_by_swift", Search),
    BottomNavigationItem("Country", "search_by_country", Search),
    BottomNavigationItem("Delete", "delete_bank", Trash),
    BottomNavigationItem("Insert", "insert_bank", Add)
)