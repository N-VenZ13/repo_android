package com.example.component_basic_jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.component_basic_jetpack.model.data.Produk
import com.example.component_basic_jetpack.model.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository): ViewModel() {
    private val _products = MutableStateFlow<List<Produk>>(emptyList())
    val products : StateFlow<List<Produk>> = _products.asStateFlow()

    private val _isloading = MutableStateFlow(false)
    val isloading : StateFlow<Boolean> = _isloading.asStateFlow()

    private val _error= MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error.asStateFlow()

    init {
        fetctProducts()
    }

    fun fetctProducts(){
        viewModelScope.launch {
            _isloading.value = true
            _error.value = null



            try {
                val productlist = repository.getProducts()
                _products.value = productlist

            } catch (e: Exception) {
                _error.value = e.message ?: "An Unknown error occured"
                _products.value = emptyList()

            } finally {
                _isloading.value = false
            }
        }
    }
}