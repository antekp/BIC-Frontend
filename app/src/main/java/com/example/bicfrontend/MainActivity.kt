package com.example.bicfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bicfrontend.navigation.ContentScreen
import com.example.bicfrontend.navigation.listOfNavigationItems
import com.example.bicfrontend.ui.theme.BackgroundColor
import com.example.bicfrontend.ui.theme.BottomBarIcon
import com.example.bicfrontend.ui.theme.BottomBoxColor
import com.example.bicfrontend.ui.theme.BoxColor
import com.example.bicfrontend.ui.theme.OrangeFill
import com.example.bicfrontend.viewmodels.BanksViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val BanksViewModel = BanksViewModel()
            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }
            Surface{
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = BottomBoxColor
                        ) {
                            listOfNavigationItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                    },
                                    icon = {
                                        BottomBarIcon(
                                            item.icon,
                                            item.title
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = item.title,
                                            color = OrangeFill
                                        )
                                    }
                                )
                            }
                        }
                    }
                ){
                    innerPadding ->
                    ContentScreen(modifier = Modifier.padding(innerPadding),viewModel = BanksViewModel, selectedIndexItem = selectedItemIndex)
                }
            }
        }
    }
}

