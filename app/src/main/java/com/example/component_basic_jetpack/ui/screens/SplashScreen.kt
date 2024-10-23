package com.example.component_basic_jetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.component_basic_jetpack.R
import kotlinx.coroutines.delay

class SplashScreen : Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        SplashScreenContent(navigator)

    }

    @Composable
    private fun SplashScreenContent(navigator: Navigator? = null){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.star_loading))
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Halooo")
                LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
            }}
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ){
////            Text(text = "Splash Screen", style = TextStyle(fontSize = 20.sp))
//            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
//        }

        LaunchedEffect(key1 = true) {
            delay(5000)
            navigator?.replace(WelcomeScreen())
        }
    }



    @Preview
    @Composable
    fun SplashScreenContentPreview(){
        SplashScreenContent()
    }




}