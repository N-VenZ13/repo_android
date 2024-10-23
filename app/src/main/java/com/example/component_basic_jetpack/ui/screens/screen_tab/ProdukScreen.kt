package com.example.component_basic_jetpack.ui.screens.screen_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.Navigator
import com.example.component_basic_jetpack.R
import com.example.component_basic_jetpack.ui.components.ImgCarouProduk
import com.example.component_basic_jetpack.ui.components.ListProduk

class ProdukScreen : Screen {
    @Composable
    override fun Content() {
        ProdukScreenContent()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ProdukScreenContent() {
        val searchQuery = remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            // Handle back action
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(
                            "List Product",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W600
                            )
                        )
                    }
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize().padding(innerPadding)
            ) {
                // Search input
                item {
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        label = { Text("Search Product") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Grid items - Membungkus LazyVerticalGrid di dalam Box dengan height yang spesifik
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)  // Berikan batasan ukuran pada Box
                    ) {
                        ListProduk()  // Panggil LazyVerticalGrid di dalam Box
                    }
                }

                // Image carousel - Sama seperti di atas, berikan batasan ukuran
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)  // Tentukan tinggi untuk kontainer ini
                    ) {
                        ImgCarouProduk(
                            images = listOf(
                                R.drawable.carou_one,
                                R.drawable.carou_two,
                                R.drawable.carou_three
                            )
                        )
                    }
                }

                // Grid items kedua
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)  // Tentukan ukuran lagi
                    ) {
                        ListProduk()  // Ulangi pola yang sama untuk grid lainnya
                    }
                }
            }

        }
    }


    @Preview
    @Composable
    private fun ProdukScreenContentPreview() {
        ProdukScreenContent()
    }
}
