package com.jonathan.jonathanau_comp304lab3_ex1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.WeatherRoutes

@Composable
fun WeatherNavigationDrawer(
    onFavoriteClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onDrawerClicked: () -> Unit = {}
){
    val items = listOf(WeatherRoutes.HomeScreen, WeatherRoutes.FavoriteScreen)
    val selectedItem = remember { mutableStateOf(items[0]) }
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Assignment 3",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(
                onClick = onDrawerClicked
            ){
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Weather Drawer Icon"
                )
            }
        }
        NavigationDrawerItem(
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
        NavigationDrawerItem(
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