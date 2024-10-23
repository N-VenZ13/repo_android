package com.example.component_basic_jetpack.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        Text(text = "profile")
    }


    @Composable
    private fun ProfileScreenContent(){

    }

}