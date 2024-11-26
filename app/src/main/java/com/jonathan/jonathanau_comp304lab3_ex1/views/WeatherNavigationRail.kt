package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.WeatherRoutes

@Composable
fun WeatherNavigationRail(
    onFavoriteClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onDrawerClicked: () -> Unit = {}
){
    val items = listOf(WeatherRoutes.HomeScreen, WeatherRoutes.FavoriteScreen)
    val selectedItem = remember { mutableStateOf(items[0]) }

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        NavigationRailItem(
            selected = false,
            onClick = onDrawerClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon"
                )
            }
        )
        NavigationRailItem(
            label = { Text(text = "Weather") },
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
        NavigationRailItem(
            label = { Text(text = "Favorites") },
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