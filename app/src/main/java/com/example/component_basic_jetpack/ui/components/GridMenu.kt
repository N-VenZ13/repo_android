package com.example.component_basic_jetpack.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.component_basic_jetpack.ui.screens.DashboardScreen
import com.example.component_basic_jetpack.ui.screens.DataListScreen
import com.example.component_basic_jetpack.ui.screens.ProfileScreen
import com.example.component_basic_jetpack.ui.screens.screen_tab.DashboardTab

@Composable
fun GridMenu(navigator: Navigator?=null) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        item {
            GridItem(icon = Icons.Default.Home, label = "Home", DashboardScreen(), navigator)
        }
        item {
            GridItem(icon = Icons.Default.Settings, label = "Settings", DataListScreen(), navigator)
        }
        item {
            GridItem(icon = Icons.Default.Star, label = "Favorite", DashboardScreen(), navigator)
        }
        item {
            GridItem(icon = Icons.Default.Share, label = "Share", DashboardScreen(),navigator)
        }
        item {
            GridItem(icon = Icons.Default.Person, label = "Profile", ProfileScreen(), navigator)
        }
    }
}

@Composable
fun GridItem(icon: ImageVector, label: String, screen: Screen, navigator: Navigator? = null) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(120.dp)
            .fillMaxWidth()
            .clickable {
                navigator?.push(screen)
            },

        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.Blue,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, color = Color.Black, modifier = Modifier.padding(8.dp))

        }
    }
}


@Preview
@Composable
fun GridMenuPreview() {
    GridMenu()

}