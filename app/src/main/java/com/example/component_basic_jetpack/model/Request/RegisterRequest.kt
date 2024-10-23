package com.example.component_basic_jetpack.model.Request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)
