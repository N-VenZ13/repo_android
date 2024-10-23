package com.example.component_basic_jetpack.model.repository

import com.example.component_basic_jetpack.manager.TokenManager
import com.example.component_basic_jetpack.model.Request.LoginRequest
import com.example.component_basic_jetpack.model.Request.RegisterRequest
import com.example.component_basic_jetpack.model.Response.ApiResponse
import com.example.component_basic_jetpack.model.data.LoginData
import com.example.component_basic_jetpack.services.ApiService

class AuthRepository(private val apiService: ApiService, private val tokenManager: TokenManager) {

    suspend fun register(name:String, email:String, password:String, password_confirmation:String):ApiResponse<Unit>{
        return apiService.register(RegisterRequest(name, email, password, password_confirmation))

    }
    suspend fun login(email: String, password: String):ApiResponse<LoginData>{
        val response = apiService.login(LoginRequest(email, password))
        if(response.status == "Success" && response.data !=null){
            val token = response.data.token
            tokenManager.saveToken(token)
        }
        return response
    }
}