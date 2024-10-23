package com.example.component_basic_jetpack.state

import android.os.Message

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
    data class Success<T>(val message: String, val data:T?) : AuthState()
}