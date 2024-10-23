package com.example.component_basic_jetpack.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.component_basic_jetpack.model.repository.ListRepository
import com.example.component_basic_jetpack.viewmodel.ListViewModel
import com.example.component_basic_jetpack.viewmodel.ProductViewModel

class ListViewModelFactory(private val repository: ListRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}