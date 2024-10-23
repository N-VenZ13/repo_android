package com.example.component_basic_jetpack.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.component_basic_jetpack.ui.screens.screen_tab.DashboardTab
import com.example.component_basic_jetpack.ui.screens.screen_tab.DataListTab
import com.example.component_basic_jetpack.ui.screens.screen_tab.ProfileTab

@Composable
fun BottomAppBar(tabNavigator: TabNavigator) {
    val navigationBarHeight = 80.dp
    val navigatorBarWidth = 250.dp
    val extraSpacing = 8.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.Cyan),
            modifier = Modifier
                .width(navigatorBarWidth)
                .height(navigationBarHeight),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            NavigationBar(
                modifier = Modifier.fillMaxSize(),
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val tabs = listOf(DashboardTab(), ProfileTab(), DataListTab())
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = tabNavigator.current.key == tab.key,
                        onClick = {
                            tabNavigator.current = tab
                            Log.d("click", "Navigation to : ${tab.key}")
                        },
                        icon = {
                            tab.options.icon?.let{
                                icon ->
                                Icon(
                                    painter = icon,
                                    contentDescription = tab.options.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(extraSpacing))
    }
}

@Preview
@Composable
fun BottomAppBarPreview(){

    TabNavigator(DashboardTab()){  tabNavigator ->
        BottomAppBar(tabNavigator)
    }

}