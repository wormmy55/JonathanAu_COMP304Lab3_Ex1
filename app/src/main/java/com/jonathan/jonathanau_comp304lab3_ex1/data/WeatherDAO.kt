package com.jonathan.jonathanau_comp304lab3_ex1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM Weather")
    fun getWeather(): Flow<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weather: List<Weather>)

    @Update
    suspend fun update(weather: WeatherEntity)

    @Query("SELECT * FROM Weather WHERE isFavorite = 1")
    fun getFavoriteWeather(): Flow<List<WeatherEntity>>
    /*
        @Query("SELECT * FROM Weather WHERE id = :id")
        suspend fun getWeatherById(id: Int): Weather?

        @Query("SELECT * FROM Weather WHERE location = :location")
        suspend fun getWeatherByLocation(location: String): Weather?

        @Query("DELETE FROM Weather WHERE id = :id")
        suspend fun deleteWeather(id: Int)

        @Query("DELETE FROM Weather")
        suspend fun deleteAll()*/
}