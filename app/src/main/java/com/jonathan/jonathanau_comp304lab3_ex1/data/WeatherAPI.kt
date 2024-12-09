package com.jonathan.jonathanau_comp304lab3_ex1.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeather(
        @Query("location") location: String,
        //@Query("api_key") apiKey: String
    ): Response<List<Weather>>
}