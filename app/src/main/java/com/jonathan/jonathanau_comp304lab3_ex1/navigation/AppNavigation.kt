package com.jonathan.jonathanau_comp304lab3_ex1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonathan.jonathanau_comp304lab3_ex1.globalIndex
import com.jonathan.jonathanau_comp304lab3_ex1.views.DailyViewScreen
import com.jonathan.jonathanau_comp304lab3_ex1.views.HomeView
import com.jonathan.jonathanau_comp304lab3_ex1.views.HourlyViewScreen
import com.jonathan.jonathanau_comp304lab3_ex1.views.WeeklyViewScreen
val APIkey = "2c6eddc6e9ad5525209749b443bb7496"
var lat = 43.6532
var lon = -79.3832
@Composable
fun AppNavigation(
    contentType: ContentType,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = WeatherRoutes.HomeScreen.route,
        //modifier = Modifier.padding(innerPadding)
    ){
        //this Route leeds to the Home Screen
        composable(WeatherRoutes.HomeScreen.route){
            HomeView(
                //This is where the routes are set
                onFavouriteButtonClick = { navController.navigate(WeatherRoutes.FavoriteScreen.route) },
                onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyScreen.route) },
                onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyScreen.route) },
                onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyScreen.route) },
            )
        }
        //This Route leads to the CreateTask Screen
        composable(WeatherRoutes.HourlyScreen.route) {
            HourlyViewScreen(
                onHomeButtonClick = { navController.navigate(WeatherRoutes.HomeScreen.route) },
                onFavouriteButtonClick = { navController.navigate(WeatherRoutes.FavoriteScreen.route) },
                onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyScreen.route) },
                onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyScreen.route) },
                listIndex = globalIndex,
            )
        }
        //This Route leeds to the ViewEdit Screen
        composable(WeatherRoutes.DailyScreen.route){
            DailyViewScreen(
                onHomeButtonClick = { navController.navigate(WeatherRoutes.HomeScreen.route) },
                onFavouriteButtonClick = { navController.navigate(WeatherRoutes.FavoriteScreen.route) },
                onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyScreen.route) },
                onWeeklyWeatherButtonClick = { navController.navigate(WeatherRoutes.WeeklyScreen.route) },
                //This sets the listIndex to the global index
                // more about this in ViewEditTasks.kt
                listIndex = globalIndex,
            )
        }
        //This Route leads to the CreateTask Screen
        composable(WeatherRoutes.WeeklyScreen.route) {
            WeeklyViewScreen(
                onHomeButtonClick = { navController.navigate(WeatherRoutes.HomeScreen.route) },
                onFavouriteButtonClick = { navController.navigate(WeatherRoutes.FavoriteScreen.route) },
                onHourlyWeatherButtonClick = { navController.navigate(WeatherRoutes.HourlyScreen.route) },
                onDailyWeatherButtonClick = { navController.navigate(WeatherRoutes.DailyScreen.route) },
                listIndex = globalIndex,
            )
        }
    }
}