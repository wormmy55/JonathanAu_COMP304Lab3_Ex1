package com.jonathan.jonathanau_comp304lab3_ex1.data

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WeatherTypeConverters {
    @TypeConverter
    fun fromWeatherList(weatherList: List<Weather>): String {
        return Json.encodeToString(weatherList)
    }
    @TypeConverter
    fun toWeatherList(weatherListString: String): List<Weather> {
        return Json.decodeFromString(weatherListString)
    }

}