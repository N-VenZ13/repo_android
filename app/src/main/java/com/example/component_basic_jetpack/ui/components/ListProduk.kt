package com.example.component_basic_jetpack.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.component_basic_jetpack.R // Gambar dari resources

@Composable
fun ListProduk() {
    val produkList = listOf(
        Produk("Sepatu Sneakers", "Sneakers stylish untuk sehari-hari", 500000, R.drawable.sneakers),
        Produk("Sepatu Formal", "Sepatu formal untuk kerja", 750000, R.drawable.formal_shoes),
        Produk("Sepatu Olahraga", "Sepatu nyaman untuk berolahraga", 350000, R.drawable.sport_shoes),
        Produk("Sepatu Boots", "Sepatu boots tahan lama", 900000, R.drawable.boots),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Menggunakan 2 kolom agar tampilan lebih lebar
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(produkList.size) { index ->
            ProdukItem(produk = produkList[index])
        }
    }
}

@Composable
fun ProdukItem(produk: Produk) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable { /* Navigasi atau aksi lainnya */ },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Menampilkan gambar sepatu
            Image(
                painter = painterResource(id = produk.imageRes),
                contentDescription = produk.nama,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(8.dp)
            )

            // Nama dan deskripsi produk
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = produk.nama,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = produk.deskripsi,
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Harga produk
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Rp ${produk.harga}",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// Model data produk
data class Produk(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val imageRes: Int
)

@Preview
@Composable
fun ListProdukPreview() {
    ListProduk()
}
