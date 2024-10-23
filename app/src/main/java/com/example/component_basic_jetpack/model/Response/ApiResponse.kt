package com.example.component_basic_jetpack.model.Response

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data:T?
)