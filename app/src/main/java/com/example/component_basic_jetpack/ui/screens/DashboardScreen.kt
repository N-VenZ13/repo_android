package com.example.component_basic_jetpack.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.component_basic_jetpack.R
import com.example.component_basic_jetpack.ui.components.BottomAppBar
import com.example.component_basic_jetpack.ui.components.GridMenu
import com.example.component_basic_jetpack.ui.components.ImageCarousel
import com.example.component_basic_jetpack.ui.screens.screen_tab.DashboardTab
import com.example.component_basic_jetpack.ui.screens.screen_tab.DataListTab
import com.example.component_basic_jetpack.ui.screens.screen_tab.ProfileTab

class DashboardScreen : Screen {
    @Composable
    override fun Content() {
        DashboardScreenContent()

    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun DashboardScreenContent() {
        val navigator = LocalNavigator.current

            GridMenu(navigator)
            TabNavigator(DashboardTab()) { tabNavigator ->
                Scaffold(
                    bottomBar = {
                        BottomAppBar(tabNavigator)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if(tabNavigator.current is DashboardTab){
                            GridMenu(navigator)
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            when (tabNavigator.current) {
                                is DashboardTab -> ImageCarousel(
                                    images = listOf(
                                        R.drawable.profile,
                                        R.drawable.ic_key,
                                        R.drawable.ic_email
                                    )
                                )

                                is ProfileTab -> ProfileScreen().Content()
                                is DataListTab -> DataListScreen().Content()
                            }
//                ImageCarousel(images = listOf(R.drawable.profile,R.drawable.ic_key ,R.drawable.ic_email))
//                Text(text = "Dashboard Screen")
                        }
                    }


                }
        }



    }


    @Preview
    @Composable
    private fun DashboardScreenContentPreview() {
        DashboardScreenContent()
    }

}