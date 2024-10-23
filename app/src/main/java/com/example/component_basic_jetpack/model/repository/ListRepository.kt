package com.example.component_basic_jetpack.model.repository

import com.example.component_basic_jetpack.model.data.Listt
import com.example.component_basic_jetpack.services.ApiService

class ListRepository(private val apiService: ApiService) {
    suspend fun getLists():List<Listt>{
        val response = apiService.getList()
        return response.data?: emptyList()
    }
}