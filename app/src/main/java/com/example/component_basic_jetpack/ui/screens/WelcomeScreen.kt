package com.example.component_basic_jetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.component_basic_jetpack.R
import com.example.component_basic_jetpack.ui.components.ButtonCustom

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        WelcomeScreenContent(navigator)
    }

    @Composable
    private fun WelcomeScreenContent(navigator: Navigator? = null) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.star_loading))

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "selamat datang di project saya", style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.W600
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Terima kasih", style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.W600
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))
                LottieAnimation(
                    modifier = Modifier
                        .width(200.dp)
                        .height(250.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                Spacer(modifier = Modifier.height(15.dp))
                ButtonCustom(
                    color = Color.Green,
                    text = "Next",
                    onclick = {
//                        navigator?.replace(LoginScreen())
                    },
                    width = 120.dp,
                    height = 50.dp
                )

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Copyright © 2024")
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }


    @Preview
    @Composable
    private fun WelcomeScreenPreview() {

        WelcomeScreenContent()
    }
}