package com.jonathan.jonathanau_comp304lab3_ex1.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Weather::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(WeatherTypeConverters::class)
abstract class WeatherDB: RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}