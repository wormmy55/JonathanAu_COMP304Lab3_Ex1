package com.jonathan.jonathanau_comp304lab3_ex1.navigation

//These are the routes for the navigation
sealed class WeatherRoutes (val route: String) {
    object HomeScreen : WeatherRoutes("home")
    object FavoriteScreen : WeatherRoutes("favourite")
    object DailyScreen : WeatherRoutes("daily")
    object HourlyScreen : WeatherRoutes("hourly")
    object WeeklyScreen : WeatherRoutes("weekly")
}
