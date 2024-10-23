package com.example.component_basic_jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.component_basic_jetpack.model.data.Listt
import com.example.component_basic_jetpack.model.data.Produk
import com.example.component_basic_jetpack.model.repository.ListRepository
import com.example.component_basic_jetpack.model.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository): ViewModel() {
    private val _list = MutableStateFlow<List<Listt>>(emptyList())
    val products : StateFlow<List<Listt>> = _list.asStateFlow()

    private val _isloading = MutableStateFlow(false)
    val isloading : StateFlow<Boolean> = _isloading.asStateFlow()

    private val _error= MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error.asStateFlow()

    init {

    }

    fun fetctListt(){
        viewModelScope.launch {
            _isloading.value = true
            _error.value = null

            try {
                val ListtList = repository.getLists()
                _list.value = ListtList
            } catch (e: Exception){
                _error.value = e.message ?: "An unknown error occured"
                _list.value = emptyList()
            } finally {
                _isloading.value = false
            }
        }
    }
}