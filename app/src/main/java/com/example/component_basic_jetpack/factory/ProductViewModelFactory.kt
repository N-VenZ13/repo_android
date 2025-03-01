package com.example.component_basic_jetpack.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.component_basic_jetpack.model.repository.ProductRepository
import com.example.component_basic_jetpack.viewmodel.ProductViewModel

class ProductViewModelFactory(private val repository: ProductRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository)as T

        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}