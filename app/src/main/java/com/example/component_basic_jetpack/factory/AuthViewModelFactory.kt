package com.example.component_basic_jetpack.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.component_basic_jetpack.manager.TokenManager
import com.example.component_basic_jetpack.model.repository.AuthRepository
import com.example.component_basic_jetpack.viewmodel.AuthViewModel

class AuthViewModelFactory(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModelProvider.Factory {
    override fun<T:ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository,  tokenManager) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}