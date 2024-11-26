package com.jonathan.jonathanau_comp304lab3_ex1.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.jonathan.jonathanau_comp304lab3_ex1.views.WeatherBottomNavigationBar
import com.jonathan.jonathanau_comp304lab3_ex1.views.WeatherNavigationRail

@Composable
fun AppNavigationContent(
    contentType: ContentType,
    navigationType: NavigationType,
    navController: NavHostController,
    onFavoriteClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onDrawerClicked: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxSize(),
    ){
        AnimatedVisibility(
            visible = navigationType == NavigationType.NavigationRail
        ){
            WeatherNavigationRail(
                onFavoriteClicked = onFavoriteClicked,
                onHomeClicked = onHomeClicked,
                onDrawerClicked = onDrawerClicked
            )
        }
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ){
                    AppNavigation(
                        contentType = contentType,
                        navController = navController,
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = navigationType == NavigationType.BottomNavigation
                )   {
                    WeatherBottomNavigationBar(
                        onFavoriteClicked = onFavoriteClicked,
                        onHomeClicked = onHomeClicked,
                    )
                }
            }
        )
    }
}