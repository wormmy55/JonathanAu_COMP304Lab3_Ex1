package com.jonathan.jonathanau_comp304lab3_ex1.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WeatherRepoImpl (
    private val weatherDAO: WeatherDAO,
    private val weatherAPI: WeatherAPI,
    private val dispatcher: CoroutineDispatcher
): WeatherRepository {
    override suspend fun getWeather(): Flow<List<Weather>> {
        return withContext(dispatcher){
            weatherDAO.getWeather()
                .map { weatherCached ->
                    weatherCached.map { weatherEntity ->
                        Weather(
                            location = weatherEntity.location,
                            temperature = weatherEntity.temperature,
                            description = weatherEntity.description,
                            longitude = weatherEntity.longitude,
                            latitude = weatherEntity.latitude,
                            isFavorite = weatherEntity.isFavorite
                        )
                    }
                }
        }
    }

        override suspend fun fetchRemoteWeather() {
            withContext(dispatcher){
                val response = weatherAPI.getWeather()
                if(response.isSuccessful){
                    response.body()!!.map {
                        weatherDAO.insert(WeatherEntity(
                            location = it.location,
                            temperature = it.temperature,
                            description = it.description,
                            longitude = it.longitude,
                            latitude = it.latitude,
                            isFavorite = it.isFavorite
                        ))
                    }
                }
            }
        }

    override suspend fun updateWeather(weather: Weather){
        withContext(dispatcher){
            weatherDAO.update(WeatherEntity(
                //id = weather.id,
                location = weather.location,
                temperature = weather.temperature,
                description = weather.description,
                longitude = weather.longitude,
                latitude = weather.latitude,
                isFavorite = weather.isFavorite
            ))
        }
    }

    override suspend fun getFavoriteWeather(): Flow<List<Weather>> {
        return withContext(dispatcher){
            weatherDAO.getFavoriteWeather()
                .map { weatherCached ->
                    weatherCached.map { weatherEntity ->
                        Weather(
                            //id = weatherEntity.id,
                            location = weatherEntity.location,
                            temperature = weatherEntity.temperature,
                            description = weatherEntity.description,
                            longitude = weatherEntity.longitude,
                            latitude = weatherEntity.latitude,
                            //timestamp = weatherEntity.timestamp,
                            isFavorite = weatherEntity.isFavorite
                        )
                    }
                }
        }
    }
    /*
        override suspend fun getFavouriteWeather(): Flow<List<Weather>> {
            return withContext(dispatcher){
                weatherDAO.getFavouriteWeather()
                    .map{ weatherCached ->
                        weatherCached.map { weatherEntity ->
                            Weather(
                                id = weatherEntity.id,
                                location = weatherEntity.location,
                                temperature = weatherEntity.temperature,
                                description = weatherEntity.description,
                                timestamp = weatherEntity.timestamp
                            )
                        }
                    }
            }
        }*/
}