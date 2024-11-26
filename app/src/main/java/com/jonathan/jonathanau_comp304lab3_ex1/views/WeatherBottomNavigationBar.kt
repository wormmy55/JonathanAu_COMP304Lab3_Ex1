package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.WeatherRoutes

@Composable
fun WeatherBottomNavigationBar(
    onFavoriteClicked: () -> Unit,
    onHomeClicked: () -> Unit
){
    val items = listOf(WeatherRoutes.HomeScreen, WeatherRoutes.FavoriteScreen)
    val selectedItem = remember { mutableStateOf(items[0]) }
    NavigationBar (
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            selected = selectedItem.value == WeatherRoutes.HomeScreen,
            onClick = {
                onHomeClicked()
                selectedItem.value = WeatherRoutes.HomeScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon"
                )
            }
        )

        NavigationBarItem(
            selected = selectedItem.value == WeatherRoutes.FavoriteScreen,
            onClick = {
                onFavoriteClicked()
                selectedItem.value = WeatherRoutes.FavoriteScreen
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon"
                )
            }
        )
    }
}