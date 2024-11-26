package com.jonathan.jonathanau_comp304lab3_ex1.data

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(): Flow<List<Weather>>
    suspend fun fetchRemoteWeather()
    suspend fun updateWeather(weather: Weather)
    //suspend fun deleteWeather(weather: Weather)
    //suspend fun addWeather(weather: Weather)
    //suspend fun getWeatherById(id: Int): Weather?
    suspend fun getFavoriteWeather(): Flow<List<Weather>>
}