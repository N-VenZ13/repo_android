package com.example.component_basic_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.Navigator
import com.example.component_basic_jetpack.factory.AuthViewModelFactory
import com.example.component_basic_jetpack.manager.TokenManager
import com.example.component_basic_jetpack.model.repository.AuthRepository
import com.example.component_basic_jetpack.services.RetrofitClient
import com.example.component_basic_jetpack.state.AuthState
import com.example.component_basic_jetpack.ui.screens.DataListScreen
import com.example.component_basic_jetpack.ui.screens.LoginScreen
import com.example.component_basic_jetpack.ui.screens.RegisterScreen
import com.example.component_basic_jetpack.ui.screens.SplashScreen
import com.example.component_basic_jetpack.ui.screens.screen_tab.ProdukScreen
import com.example.component_basic_jetpack.ui.theme.Component_Basic_jetpackTheme
import com.example.component_basic_jetpack.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    private lateinit var tokenManager:TokenManager
    private lateinit var authRepository: AuthRepository
    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tokenManager = TokenManager(applicationContext)
        authRepository = AuthRepository(RetrofitClient.apiService,tokenManager)
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(authRepository,tokenManager))[AuthViewModel::class.java]


        setContent {
            Component_Basic_jetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
//                    Navigator(ProdukScreen())
                    Navigator(ProdukScreen())
                }
            }
        }
    }
}
