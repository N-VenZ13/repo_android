package com.example.component_basic_jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.component_basic_jetpack.manager.TokenManager
import com.example.component_basic_jetpack.model.Response.ApiResponse
import com.example.component_basic_jetpack.model.repository.AuthRepository
import com.example.component_basic_jetpack.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository,private val tokenManager: TokenManager) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authstate: StateFlow<AuthState> = _authState

    suspend fun getToken():String?{
        return tokenManager.getToken()
    }

    fun register(name:String, email:String, password:String,passwordConfirmation:String){
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.register(name, email, password, passwordConfirmation)
                handleResponse(response)
            } catch (e:Exception){
                _authState.value = AuthState.Error(e.message ?: "Unknown Error occured")
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.login(email, password)
                handleResponse(response)
            }
            catch (e:Exception){
                _authState.value = AuthState.Error(e.message ?:"Unknown Error occured")
            }
        }
    }

    private fun <T> handleResponse(response: ApiResponse<T>){
        if(response.status == "Success"){
            _authState.value = AuthState.Success(response.message,response.data)
        }else{
            _authState.value = AuthState.Error(response.message)
        }
    }


}