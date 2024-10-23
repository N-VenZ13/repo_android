package com.example.component_basic_jetpack.ui.screens.screen_tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.airbnb.lottie.animation.content.Content
import com.example.component_basic_jetpack.R
import com.example.component_basic_jetpack.ui.screens.DashboardScreen
import com.example.component_basic_jetpack.ui.screens.DataListScreen

class DashboardTab : Tab{
    override val options: TabOptions
    @Composable
    get() = TabOptions(
        index = 0u,
        title = "Dashboard",
        icon = painterResource(id = R.drawable.ic_home)
    )


    @Composable
    override fun Content(){
        DashboardScreen().Content()
    }
}

class ProfileTab : Tab{
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Profile",
            icon = painterResource(id = R.drawable.ic_people)
        )


    @Composable
    override fun Content(){
        DashboardScreen().Content()
    }
}

class DataListTab : Tab{
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = "Data List",
            icon = painterResource(id = R.drawable.ic_people)
        )


    @Composable
    override fun Content(){
        DataListScreen().Content()
    }
}
