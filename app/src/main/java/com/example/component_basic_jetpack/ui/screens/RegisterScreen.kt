package com.example.component_basic_jetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.component_basic_jetpack.state.AuthState
import com.example.component_basic_jetpack.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class RegisterScreen(private val viewModel: AuthViewModel) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val scope = rememberCoroutineScope()
        var name by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var passwordConfirmation by remember {
            mutableStateOf("")
        }

        val authState by viewModel.authstate.collectAsState()

        RegisterContent(
            name = name,
            email = email,
            password = password,
            passwordConfirmation = passwordConfirmation,
            onNameChange = {name = it},
            onPasswordChange = {password = it},
            onEmailChange = {email = it},
            onPasswordConfirmationChange = {passwordConfirmation = it},
            onRegisterClick = {
                scope.launch {
                    viewModel.register(name, email, password, passwordConfirmation)
                }
            },
            authState = authState
        )




    }

}

@Composable
fun RegisterContent(
    name: String,
    email: String,
    password: String,
    passwordConfirmation: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmationChange: (String) ->Unit,
    onRegisterClick: () -> Unit,
    authState: AuthState

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordConfirmation,
            onValueChange = onPasswordConfirmationChange,
            label = { Text(text = "Password Confirmation") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRegisterClick
//        {
//            scope.launch {
//                viewModel.register(name, email, password, passwordConfirmation)
//            } }
            , modifier = Modifier.fillMaxWidth()) {
            Text("Register")

        }

        when(authState){
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success<*> -> {
                Text(text = "Success : ${(authState as AuthState.Success<*>).message}")
            }
            is  AuthState.Error -> Text(text = "Error : ${(authState as AuthState.Error).message}")
            else -> {

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview(){
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfirmation by remember {
        mutableStateOf("")
    }
    var authState by remember {
        mutableStateOf<AuthState>(AuthState.Idle)
    }
    RegisterContent(
        name = name,
        email = email,
        password = password,
        passwordConfirmation = passwordConfirmation,
        onNameChange = {name = it},
        onPasswordChange = {password = it},
        onEmailChange = {email = it},
        onPasswordConfirmationChange = {passwordConfirmation = it},
        onRegisterClick = {},
        authState = authState
    )
}