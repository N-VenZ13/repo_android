package com.example.component_basic_jetpack.model.repository

import com.example.component_basic_jetpack.model.data.Produk
import com.example.component_basic_jetpack.services.ApiService

class ProductRepository(private val apiService: ApiService){
    suspend fun getProducts():List<Produk>{
        val response = apiService.getProduct()
        return response.data?: emptyList()

    }
}