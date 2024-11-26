package com.jonathan.jonathanau_comp304lab3_ex1.data

import retrofit2.http.GET

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeather(): Weather
}