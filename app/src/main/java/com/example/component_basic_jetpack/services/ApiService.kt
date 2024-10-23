package com.example.component_basic_jetpack.services

import com.example.component_basic_jetpack.constants.AppConfigurations
import com.example.component_basic_jetpack.model.Request.LoginRequest
import com.example.component_basic_jetpack.model.Request.RegisterRequest
import com.example.component_basic_jetpack.model.Response.ApiResponse
import com.example.component_basic_jetpack.model.data.Listt
import com.example.component_basic_jetpack.model.data.LoginData
import com.example.component_basic_jetpack.model.data.Produk
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(AppConfigurations.EndPoints.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest):ApiResponse<LoginData>

    @POST(AppConfigurations.EndPoints.REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest):ApiResponse<Unit>

    @GET(AppConfigurations.EndPoints.PRODUCT)
    suspend fun getProduct():ApiResponse<List<Produk>>

    @GET(AppConfigurations.EndPoints.LIST)
    suspend fun getList():ApiResponse<List<Listt>>

}