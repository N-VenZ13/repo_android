package com.example.component_basic_jetpack.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.component_basic_jetpack.model.data.Person
import com.example.component_basic_jetpack.model.data.Produk
import com.example.component_basic_jetpack.model.repository.ProductRepository
import com.example.component_basic_jetpack.services.RetrofitClient
import com.example.component_basic_jetpack.ui.components.PersonList
import com.example.component_basic_jetpack.viewmodel.ProductViewModel
import java.util.logging.ErrorManager

class DataListScreen : Screen {
    private val viewModel: ProductViewModel by lazy {
        val apiService = RetrofitClient.apiService
        val repository = ProductRepository(apiService)
        ProductViewModel(repository)
    }

    @Composable
    override fun Content() {
        DataListScreenContent(viewModel)
    }

    @Composable
    private fun DataListScreenContent(viewModel: ProductViewModel) {
        val allProducts by viewModel.products.collectAsState()
        val isLoading by viewModel.isloading.collectAsState()
        val error by viewModel.error.collectAsState()

        var displayProducts by remember {
            mutableStateOf<List<Produk>>(emptyList())
        }

        var currentIndex by remember {
            mutableStateOf(0)
        }

        val pageSize = 10
        val listState = rememberLazyListState()

        LaunchedEffect(allProducts) {
            if (allProducts.isNotEmpty() && displayProducts.isEmpty()) {
                displayProducts = allProducts.take(pageSize)
                currentIndex = pageSize

            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(state = listState) {
                itemsIndexed(displayProducts) { index, product ->
                    ProductItem(product = product)

                    if (index == displayProducts.lastIndex && currentIndex < allProducts.size) {
                        LaunchedEffect(Unit) {
                            val nextItem = allProducts.subList(
                                currentIndex,
                                (currentIndex + pageSize).coerceAtMost(allProducts.size)
                            )
                            displayProducts = displayProducts + nextItem
                            currentIndex += pageSize


                        }
                    }
                }
                item {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            if (error != null) {
                ErrorMessage(error = error)
            }

            if (allProducts.isEmpty() && !isLoading) {
                EmptyState()
            }
        }
    }

    @Composable
    private fun ProductItem(product: Produk) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = product.judul, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
                Text(text = product.jumlah, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    text = product.slug,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        }
    }

    @Composable
    private fun ErrorMessage(error: String?) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Error: $error")
        }
    }

    @Composable
    private fun EmptyState() {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "No Data")
        }
    }

    @Preview
    @Composable
    private fun DataListScreenContentPreview() {
        DataListScreenContent(viewModel)
    }


}