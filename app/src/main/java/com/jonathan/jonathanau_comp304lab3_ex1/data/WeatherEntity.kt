package com.jonathan.jonathanau_comp304lab3_ex1.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Weather", primaryKeys = ["longitude", "latitude"])
data class WeatherEntity (
    //val id: String,
    val location: String,
    val temperature: String,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    //val timestamp: Long = System.currentTimeMillis(),
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean
)