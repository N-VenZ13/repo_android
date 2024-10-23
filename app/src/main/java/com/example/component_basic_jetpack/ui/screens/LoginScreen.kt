package com.example.component_basic_jetpack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.component_basic_jetpack.R
import com.example.component_basic_jetpack.model.data.LoginData
import com.example.component_basic_jetpack.state.AuthState
import com.example.component_basic_jetpack.ui.components.GradientButton
import com.example.component_basic_jetpack.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



class LoginScreen(private val viewModel: AuthViewModel) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val authState by viewModel.authstate.collectAsState()
        val scope = rememberCoroutineScope()
        var token by remember { mutableStateOf<String?>(null) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        loginContent(
            email = email,
            password = password,
            onEmailChange = {email=it},
            onPasswordChange = {password=it},
            onLoginClick = {
                scope.launch {
                    viewModel.login(email, password)
                }
            },
            authState = authState
        )

//        val scope = rememberCoroutineScope()
//        val authState by viewModel.authstate.collectAsState()
//        var token by remember { mutableStateOf<String?>(null) }




//        LoginScreen(navigator, scope)
    }
}

@Composable
fun loginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    authState: AuthState
){
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center).size(150.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Welcome back User",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(value = email, onValueChange = onEmailChange,
                placeholder = {
                    Text(text = "Masukkan email anda")
                },
                label = {
                    Text(text = "Email")
                }, leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "email"
                    )
                })
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(value = password, onValueChange = onPasswordChange,
                placeholder = {
                    Text(text = "Masukkan Password anda")
                },
                label = {
                    Text(text = "Password")
                }, leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_key),
                        contentDescription = "password"
                    )
                })
            Spacer(modifier = Modifier.height(10.dp))

            GradientButton(
                text = "Submit", gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFB3B1AE), Color(0xFFD8D2C2))
                ), textcolor = Color(0xFF001F3F)
            )
            {
                    onLoginClick()
                }
            when (authState) {
                is AuthState.Loading -> CircularProgressIndicator()
                is AuthState.Success<*> -> {
                    val data = (authState as AuthState.Success<*>).data
                    if (data is LoginData) {
//                        LaunchedEffect(Unit) {
//                            scope.launch {
//                                token = viewModel.getToken().toString()
//                            }
//                        }
//                        token?.let { Text(text = it) }
                        Text(text = "Welcome user, ${data.user.name}")
                        Text(text = "token ${data.token}")
                    } else {
                        Text(text = "Success : ${(authState as AuthState.Success<*>).message}")
                    }
                }

                is AuthState.Error -> Text(text = "Error : ${(authState as AuthState.Error).message}")
                else -> {

                }
            }
            }



        }
    }

@Preview(showBackground = true)
@Composable
fun LoginContentPreview(){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var authState by remember {
        mutableStateOf<AuthState>(AuthState.Idle)
    }

    loginContent(
        email = email,
        password = password,
        onEmailChange = {email=it},
        onPasswordChange = {password=it},
        onLoginClick = {},
        authState = authState
    )

}

