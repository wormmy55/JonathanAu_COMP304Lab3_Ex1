package com.jonathan.jonathanau_comp304lab3_ex1.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherAPI
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherDB
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherRepoImpl
import com.jonathan.jonathanau_comp304lab3_ex1.data.WeatherRepository
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.APIkey
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.lat
import com.jonathan.jonathanau_comp304lab3_ex1.navigation.lon
import com.jonathan.jonathanau_comp304lab3_ex1.viewmodel.WeatherViewModel
import com.jonathan.jonathanau_comp304lab3_ex1.workers.WeatherSyncWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
}

//API call
//https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}

//API call: Coordinates by location name
//http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}

val appModules = module {
    single<WeatherRepository> { WeatherRepoImpl(get(), get(), get())}
    single { Dispatchers.IO }
    single { WeatherViewModel(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/3.0/onecall?lat=" + {lat}+ "&lon=" + {lon} + "&appid=" + {APIkey})
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .build()
    }
    single { get<Retrofit>().create(WeatherAPI::class.java) }
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDB::class.java,
            "weather-database"
        ).build()
    }
    single { get<WeatherDB>().weatherDao() }
    worker { WeatherSyncWorker(get(), get(), get()) }
}